package com.dhinojosac.android.inverttext;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity implements MainView {
    @Bind(R.id.btnStart)
    Button btnStart;
    @Bind(R.id.progressBar)
    ProgressBar progressBar;
    @Bind(R.id.editText)
    EditText editText;
    @Bind(R.id.textViewResult)
    TextView textViewResult;
    @Bind(R.id.btnCopy)
    Button btnCopy;

    private Presenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        presenter = new PresenterImpl(this);
        presenter.onCreate();

    }

    @Override
    public void showProgress() {
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgress() {
        progressBar.setVisibility(View.GONE);
    }

    @Override
    public void enableInputs() {
        btnStart.setEnabled(true);
    }

    @Override
    public void disableInputs() {
        btnStart.setEnabled(false);
    }

    @Override
    public void enableCopy() {
        btnCopy.setEnabled(true);
    }

    @Override
    public void disableCopy() {
        btnCopy.setEnabled(false);
    }

    @Override
    public void resetInput() {
        editText.setText("");
    }

    @Override
    public void showAnswer(String text) {
        textViewResult.setText(text);
    }

    @OnClick(R.id.btnStart)
    public void sendText(View v) {
        hideSoftKeyboard();
        if (editText.length() > 0) {
            String text = editText.getText().toString();
            Log.d("text-Main", text.toString());
            presenter.sendText(text);
        } else {
            Toast.makeText(this, "Tiene que ingresar texto", Toast.LENGTH_SHORT).show();
            Log.d("EmptyText", "Sin valor en editText");
        }
    }

    @OnClick(R.id.btnCopy)
    public void copyText(View v) {
        String text = textViewResult.getText().toString();
        ClipboardManager clipboard = (ClipboardManager) getSystemService(CLIPBOARD_SERVICE);
        ClipData clip = ClipData.newPlainText("LABEL", text);
        clipboard.setPrimaryClip(clip);

        Toast.makeText(this, "Resultado guardado en portapapeles", Toast.LENGTH_SHORT).show();

    }
    public void hideSoftKeyboard() {
        if(getCurrentFocus()!=null) {
            InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
            inputMethodManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);
        }
    }

}
