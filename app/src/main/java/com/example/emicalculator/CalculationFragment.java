package com.example.emicalculator;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;
import com.example.emicalculator.databinding.FragmentCalculationBinding;

public class CalculationFragment extends Fragment {

    Bundle bundle;
    PaymentCalculation paymentCalculation;
    private FragmentCalculationBinding binding;
    private TextView totalText;
    private TextView mortgageText;
    private TextView interestText;
    private TextView amortizationText;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        binding = FragmentCalculationBinding.inflate(inflater, container, false);

        bundle = this.getArguments();
        if (bundle != null) {
            paymentCalculation = (PaymentCalculation) bundle.getSerializable("calculation");
        }

        return binding.getRoot();

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        totalText = binding.totalText;
        mortgageText = binding.mortgageVal;
        interestText = binding.interestVal;
        amortizationText = binding.amortizationVal;

        String[] values = paymentCalculation.getValues();

        double totalVal = (double) Math.round(paymentCalculation.calculatePayment() * 100d) / 100d;

        String formattedAmortization = values[2].substring(0,values[2].indexOf('.'));

        totalText.setText(String.format("$%s", totalVal));
        mortgageText.setText(String.format("$%s", values[0]));
        interestText.setText(String.format("%s%%", values[1]));
        amortizationText.setText(String.format("%s year(s)", formattedAmortization));

        binding.recalculateBtn.setOnClickListener(v -> {
            NavHostFragment.findNavController(CalculationFragment.this)
                    .navigate(R.id.action_SecondFragment_to_FirstFragment);
        });

        binding.reminderBtn.setOnClickListener(v -> {
            Intent intent = new Intent(Intent.ACTION_DIAL);
            intent.setData(Uri.parse("tel:18662223456"));
            startActivity(intent);

        });

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}