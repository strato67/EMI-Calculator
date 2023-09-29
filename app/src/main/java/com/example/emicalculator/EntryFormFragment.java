package com.example.emicalculator;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import com.example.emicalculator.databinding.FragmentEntryFormBinding;

import java.io.Serializable;


public class EntryFormFragment extends Fragment {
    private FragmentEntryFormBinding binding;
    private EditText principalField;
    private EditText interestField;
    private EditText amortizatonField;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentEntryFormBinding.inflate(inflater, container, false);
        // Inflate the layout for this fragment
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        principalField = binding.principalAmountField;
        interestField = binding.interestRateField;
        amortizatonField = binding.amortizationField;

       PaymentCalculation paymentCalculation = new PaymentCalculation();

        binding.submitBtn.setOnClickListener(v -> {

            String principalVal = principalField.getText().toString();
            String interestVal = interestField.getText().toString();
            String amortizatonVal = amortizatonField.getText().toString();

            if (principalVal.isEmpty() || interestVal.isEmpty() || amortizatonVal.isEmpty()) {
                Toast.makeText(getContext(), "All fields are required.", Toast.LENGTH_SHORT).show();
            } else {
                paymentCalculation.setValues(principalVal, interestVal, amortizatonVal);
                Bundle bundle = new Bundle();
                bundle.putSerializable("calculation",  paymentCalculation);
                NavHostFragment.findNavController(EntryFormFragment.this)
                        .navigate(R.id.action_FirstFragment_to_SecondFragment, bundle);
            }

        });

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}