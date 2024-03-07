package com.example.rallyup.qrGeneration;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.NumberPicker;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import com.example.rallyup.FirestoreController;
import com.example.rallyup.R;
import com.example.rallyup.firestoreObjects.Event;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.journeyapps.barcodescanner.BarcodeEncoder;

import java.io.IOException;
import java.util.Calendar;

public class AddEvent extends AppCompatActivity {
    private EditText eventLocationInput, eventNameInput, eventDescriptionInput;

    private TextView eventDateInput, eventTimeInput, uploadPosterText;

    private Button createButton;

    // b2 is the back button
    private FloatingActionButton eventImageInput, b2;

    private CheckBox geoInput, newQRSelect, attendeeSignUpLimitInput, reUseQRSelect;

    private NumberPicker attendeeLimitPicker;

    private String eventName, eventLocation, eventDescription, eventID;
    private StorageReference posterRef, shareQRRef, checkInQRRef;

    // Date in the format year, month, day concatenated together
    // time in the format hour, minute concatenated together in 24 hour time
    private String eventDate, eventTime;
    private Integer signupLimit = 0;
    private Boolean geolocation, signupLimitInput, reUseQR, newQR;

    private ImageView qrView, posterImage = null;

    private FirebaseStorage storage;
    // Create a storage reference from our app
    private StorageReference storageRef;

    // Create a child reference
    // imagesRef now points to "images"
    private FirestoreController controller = new FirestoreController();

    private Uri image = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_event);

        initializeViews();

        // prompting the user to upload an image when they click on the upload box
        eventImageInput.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                imageChooser();
            }
        });

        /**
         * Setting the onClick Listener for picking the date, then setting the text of the pick date button
         * (Clickable textView) To be that selected date
         */
        eventDateInput.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getEventDate();
            }
        });

        // prompting the user to set event start time when the "Event Time" box is clicked
        eventTimeInput.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getEventTime();
            }
        });

        attendeeSignUpLimitInput.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView,
                                         boolean isChecked) {
                if (isChecked) {
                    setAttendeeLimit();
                } else {
                    resetAttendeeLimit();
                }
            }
        });

        attendeeLimitPicker.setOnScrollListener(new NumberPicker.OnScrollListener() {
            @Override
            public void onScrollStateChange(NumberPicker numberPicker, int scrollState) {
                if (scrollState == NumberPicker.OnScrollListener.SCROLL_STATE_IDLE) {
                    //We get the different between oldValue and the new value
                    signupLimit = numberPicker.getValue();
                }
            }
        });

        attendeeLimitPicker.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
            @Override
            public void onValueChange(NumberPicker picker, int oldVal, int newVal){
                if(oldVal != newVal) {
                    signupLimit = attendeeLimitPicker.getValue();
                }
            }
        });


        // Generating the new QR Code that is encoded with the event name when the user checks the
        // "Generate new QR Code" box
        newQRSelect.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView,
                                         boolean isChecked) {
                if (isChecked) {
                    generateShareQR();
                } else {
                    resetQR();
                }
            }
        });


        createButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onCreateEventClick();
            }
        });


        b2.setOnClickListener(v -> {
            // for clicking the button to go back to the QR page
            switchPage();
        });


    }

    public void initializeViews() {
        b2 = findViewById(R.id.pageSwitcher2);

        eventNameInput = findViewById(R.id.eventNameInput);
        uploadPosterText = findViewById(R.id.uploadPosterText);
        eventLocationInput = findViewById(R.id.eventLocationInput);
        eventDateInput = findViewById(R.id.eventDateInput);
        eventTimeInput = findViewById(R.id.eventTimeInput);
        eventDescriptionInput = findViewById(R.id.eventDetailsInput);
        attendeeSignUpLimitInput = findViewById(R.id.attendeeSignUpLimitInput);
        attendeeLimitPicker = findViewById(R.id.attendeeLimitPicker);
        geoInput = findViewById(R.id.geolocationInput);
        createButton = findViewById(R.id.createEventButton);
        eventImageInput = findViewById(R.id.posterUploadButton);
        qrView = findViewById(R.id.qrCodeView);
        newQRSelect = findViewById(R.id.newQrCodeSelect);
        reUseQRSelect = findViewById(R.id.reuseQRCodeSelect);
        posterImage = findViewById(R.id.uploadPosterView);

    }


    /**
     * Allows the user to upload an image from their device
     * to represent the poster for this event.
     * This method does not take in any parameters, or return any variables
     */
    private void imageChooser()
    {
        Intent i = new Intent();
        i.setType("image/*");
        i.setAction(Intent.ACTION_GET_CONTENT);

        launchSomeActivity.launch(i);
    }


    ActivityResultLauncher<Intent> launchSomeActivity
            = registerForActivityResult(
            new ActivityResultContracts
                    .StartActivityForResult(),
            result -> {
                if (result.getResultCode()
                        == Activity.RESULT_OK) {
                    Intent data = result.getData();
                    // do your operation from here....
                    if (data != null
                            && data.getData() != null) {
                        Uri selectedImageUri = data.getData();
                        image = selectedImageUri;
                        Bitmap selectedImageBitmap = null;
                        try {
                            selectedImageBitmap
                                    = MediaStore.Images.Media.getBitmap(
                                    this.getContentResolver(),
                                    selectedImageUri);
                        }
                        catch (IOException e) {
                            e.printStackTrace();
                        }
                        posterImage.setImageBitmap(
                                selectedImageBitmap);
                        uploadPosterText.setVisibility(uploadPosterText.GONE);
                    }
                }
            });

    /**
     * Prompts the user to set the start date of this event by
     * utilizing the Calender widget. Saves the inputted date as a String
     * that represents Year, Month, and Day concatenated together in that order for ease of sorting.
     * This method does not take in any parameters, or return any variables
     */
    public void getEventDate(){
        // on below line we are getting
        // the instance of our calendar.
        final Calendar c = Calendar.getInstance();

        // on below line we are getting
        // our day, month and year.
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH);
        int day = c.get(Calendar.DAY_OF_MONTH);

        // on below line we are creating a variable for date picker dialog.
        DatePickerDialog datePickerDialog = new DatePickerDialog(
                // on below line we are passing context.
                AddEvent.this,
                new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year,
                                          int monthOfYear, int dayOfMonth) {
                        // on below line we are setting date to our text view.
                        monthOfYear = monthOfYear + 1;
                        eventDateInput.setText(toStringCheckZero(dayOfMonth) + "-" + toStringCheckZero((monthOfYear)) + "-" + toStringCheckZero(year));
                        eventDate = (toStringCheckZero(year) + toStringCheckZero((monthOfYear)) + toStringCheckZero(dayOfMonth));

                    }
                },
                // on below line we are passing year,
                // month and day for selected date in our date picker.
                year, month, day);
        // at last we are calling show to
        // display our date picker dialog.
        datePickerDialog.show();

    }

    /**
     * Prompts the user to set the start time of this event by
     * utilizing the TimePicker widget. Saves the inputted time as a String
     * that represents hour and minute concatenated together in that order for ease of sorting.
     * Set to the 24 Hour clock
     * This method does not take in any parameters, or return any variables
     */
    public void getEventTime(){
        Calendar mcurrentTime = Calendar.getInstance();
        int hour = mcurrentTime.get(Calendar.HOUR_OF_DAY);
        int minute = mcurrentTime.get(Calendar.MINUTE);
        TimePickerDialog mTimePicker;
        mTimePicker = new TimePickerDialog(AddEvent.this, new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute) {
                eventTimeInput.setText("" + toStringCheckZero(selectedHour) + ":" + toStringCheckZero(selectedMinute));
                eventTime = toStringCheckZero(selectedHour) + toStringCheckZero(selectedMinute);
            }
        }, hour, minute, true); //Setting it to 24 hour time
        mTimePicker.setTitle("Select Time");
        mTimePicker.show();
    }

    public void setAttendeeLimit() {
        attendeeLimitPicker.setVisibility(attendeeLimitPicker.VISIBLE);
        attendeeLimitPicker.setMaxValue(1000);
        attendeeLimitPicker.setMinValue(1);
    }

    public void resetAttendeeLimit() {
        attendeeLimitPicker.setValue(1);
        attendeeLimitPicker.setVisibility(attendeeLimitPicker.GONE);
    }


    public void switchPage() {
        Intent a = new Intent(AddEvent.this, com.example.rallyup.qrGeneration.MainActivity.class);
        a.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
        startActivity(a);
    }

    public String toStringCheckZero(int number){
        if(number<=9) {
            String fixedNum = "0" + String.valueOf(number);
            return fixedNum;
        }
        return String.valueOf(number);
    }


    /**
     * Generates a QR Code that contains the name of the event
     * This method does not take in any parameters, or return any variables
     */
    private void generateShareQR() {
        String text = eventNameInput.getText().toString();
        MultiFormatWriter writer = new MultiFormatWriter();
        BitMatrix matrix;
        try {
            matrix = writer.encode(text, BarcodeFormat.QR_CODE, 400, 400);
        } catch (WriterException e) {
            throw new RuntimeException(e);
        }
        BarcodeEncoder encoder = new BarcodeEncoder();
        Bitmap bitmap = encoder.createBitmap(matrix);
        qrView.setVisibility(qrView.VISIBLE);
        qrView.setImageBitmap(bitmap);
    }



    /**
     * Resets the QR Code View to be hidden
     * This method does not take in any parameters, or return any variables
     */
    public void resetQR(){
        //qrView = null;
        qrView.setVisibility(qrView.GONE);
    }


    /**
     * Returns a Boolean object that represents whether or not all required fields have been filled in.
     * <p>
     * This method ensures that all the inputs required for successful event creation have been filled in.
     *
     * @return      the Boolean value representing whether or not all required fields have been filled in.
     */
    public Boolean validateInput() {
        if(eventName.isEmpty() || eventLocation.isEmpty() || (eventTimeInput.getText().toString().isEmpty())
        || eventDateInput.getText().toString().isEmpty() || !newQRSelect.isChecked() || posterImage.equals(null)
        || qrView.equals(null) || image.equals(null)) {
            Toast.makeText(
                            this,
                            "Please ensure all fields are filled out!",
                            Toast.LENGTH_SHORT)
                    .show();
            return false;
        }
        else {
            return true;
        }
    }

    public void uploadPoster() {
        controller.uploadImage(image, posterRef);

    }

    public void uploadShareQR() {
        controller.uploadImageBitmap(qrView, shareQRRef);
    }

    public void uploadCheckInQR() {
        controller.uploadImageBitmap(qrView, checkInQRRef);
    }

    /**
     * This method saves all the input fields in new variables, resets the views of all the input fields on the screen,
     * and passes the saved variables to firebase as an instance of the Event Class.
     *
     */
    public void onCreateEventClick(){
        eventName = String.valueOf(eventNameInput.getText());
        eventLocation = String.valueOf(eventLocationInput.getText());
        geolocation = geoInput.isChecked();
        eventDescription = String.valueOf(eventDescriptionInput.getText());
        signupLimitInput = attendeeSignUpLimitInput.isChecked();
        newQR = newQRSelect.isChecked();
        reUseQR = reUseQRSelect.isChecked();

        Boolean inputVal = validateInput();
        if(inputVal.equals(true)) {
            storage = FirebaseStorage.getInstance();
            storageRef = storage.getReference();
            posterRef = storageRef.child("images/Posters/"+ eventName);
            shareQRRef = storageRef.child("images/ShareQR/"+ eventName);
            checkInQRRef = storageRef.child("images/CheckInQR/"+ eventName);

            uploadPoster();
            uploadCheckInQR();
            uploadShareQR();
            eventNameInput.getText().clear();
            eventLocationInput.getText().clear();
            eventDescriptionInput.getText().clear();
            eventDateInput.setText("");
            eventTimeInput.setText("");
            geoInput.setChecked(false);
            newQRSelect.setChecked(false);
            attendeeSignUpLimitInput.setChecked(false);
            attendeeLimitPicker.setVisibility(attendeeLimitPicker.GONE);
            //posterImage = null;
            //qrView = null;

            // send values to fb
            Event newEvent = new Event(eventName, eventLocation, eventDescription,
                    eventDate, eventTime, signupLimit, signupLimitInput,
                    geolocation, reUseQR, newQR,
                    posterRef, shareQRRef, checkInQRRef);
            FirestoreController fc = FirestoreController.getInstance();
            fc.addEvent(newEvent);

            // SWITCHING THE PAGE TO THE IMAGE UPLOAD TUTORIAL PAGE
            Intent a = new Intent(AddEvent.this, com.example.rallyup.qrGeneration.imageLoadTestActivity.class);
            a.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
            startActivity(a);

        }
    }
}
