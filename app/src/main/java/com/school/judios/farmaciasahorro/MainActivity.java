package com.school.judios.farmaciasahorro;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.io.FileNotFoundException;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    TextView textTargetUri;
    ImageView targetImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ImageButton buttonLoadImage = (ImageButton) findViewById(R.id.Fotopaciente);
        textTargetUri = (TextView) findViewById(R.id.targeturi);
        targetImage = (ImageView) findViewById(R.id.Ohnolafoto);

        Spinner spinner = (Spinner) findViewById(R.id.doctor_spinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.Drs, R.layout.support_simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(this);

        buttonLoadImage.setOnClickListener(new Button.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                // TODO Auto-generated method stub
                Intent intent = new Intent(Intent.ACTION_PICK,
                        android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(intent, 0);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        // TODO Auto-generated method stub
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK) {
            Uri targetUri = data.getData();
            textTargetUri.setText(targetUri.toString());
            Bitmap bitmap;
            try {
                bitmap = BitmapFactory.decodeStream(getContentResolver().openInputStream(targetUri));
                targetImage.setImageBitmap(bitmap);
            } catch (FileNotFoundException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }


    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        Toast.makeText(this, adapterView.getItemAtPosition(i).toString(), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {
        Toast.makeText(this, "selection disappears", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }

    public void Toast (View v) {
        EditText Uno = (EditText) findViewById(R.id.Celda_1);
        EditText Dos = (EditText) findViewById(R.id.Celda_2);
        EditText Tres = (EditText) findViewById(R.id.Celda_7);
        EditText Cuatro = (EditText) findViewById(R.id.Celda_8);
        EditText V = (EditText) findViewById(R.id.Celda_4);
        EditText VI = (EditText) findViewById(R.id.Celda_3);
        EditText VII = (EditText) findViewById(R.id.Celda_5);
        EditText VIII = (EditText) findViewById(R.id.Celda_6);
        Spinner IX = (Spinner) findViewById(R.id.doctor_spinner);
        Toast.makeText(MainActivity.this, "Ell paciente es: " + Uno.getText() + " con " + Dos.getText() + " años de edad. " + " Su cita es el dia " +
                        Tres.getText() + " a las " + Cuatro.getText() + " Su celular es:" + V.getText() + " con el siguiente correo:" + VI.getText() +
                        " y dirección: " + VII.getText() + " con un Padecimiento de " + VIII.getText() + " y será atendido por el Doctor: " + IX.getSelectedItem().toString(),
                Toast.LENGTH_LONG).show();

    }
}



