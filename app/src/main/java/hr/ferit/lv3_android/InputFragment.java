package hr.ferit.lv3_android;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;


public class InputFragment extends Fragment {
    private EditText mEnterText;
    private Button mSubmitButton;

    private ButtonClickListener mListener;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_input, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mEnterText = view.findViewById(R.id.edMessage);
        mSubmitButton = view.findViewById(R.id.btSubmit);
        setUpListener();
    }

    private void setUpListener() {
        mSubmitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mListener != null)
                    mListener.onButtonClicked(mEnterText.getText().toString());
            }
        });
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if(context instanceof ButtonClickListener){
            mListener = (ButtonClickListener) context;

        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;

    }
}