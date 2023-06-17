package com.example.stoplyingdown;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Diet3Fragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Diet3Fragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public Diet3Fragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Diet3Fragment.
     */
    // TODO: Rename and change types and number of parameters
    public static Diet3Fragment newInstance(String param1, String param2) {
        Diet3Fragment fragment = new Diet3Fragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View inflatedView = inflater.inflate(R.layout.fragment_diet3, container, false);
        TextView back = inflatedView.findViewById(R.id.back_button);
        TextView title = inflatedView.findViewById(R.id.title);
        TextView dietText = inflatedView.findViewById(R.id.dietText);
        title.setText("Набор массы");
        dietText.setText("Что же необходимо учитывать при составлении меню для набора мышечной массы?\n" +
                "   1. Питание должно быть дробным. То есть питайтесь часто, 4-6 раз в день, но небольшими порциями. Так вы насыщаете организм энергией, но не накапливаете жир.\n" +
                "   2. Несмотря на небольшие размеры порций продукты в рационе при наборе мышечной массы должны быть высококалорийными.\n" +
                "   3. Исключить быстрые углеводы и жиры - сладкое, мучное.\n" +
                "   4. Следите за количеством выпитой воды в сутки. Для обычного человека это 30-40 мл на килограмм веса. При диете для набора мышечной массы рекомендуется выпивать около трех литров воды в сутки, чтобы избежать дегидратации при разгоне метаболизма.\n" +
                "   5. До 16 часов употребляется большая часть дневного рациона - завтрак, обед и два перекуса.\n" +
                "   6. Разработайте план достаточно интенсивных тренировок на разные группы мышц, чтобы употребляемые калории превращались в мышцы, а не в жир.\n" +
                "   Рассмотрим суточное меню при наборе мышечной массы:\n" +
                "Процентное соотношение жиров, белков и углеводов высчитываем следующим образом:\n" +
                "  Углеводы- 55-50%\n  Белки- 25-30%\n  Жиры- 10-20%\n" +
                "Сколько же нужно калорий в сутки именно вам, считаем по формуле: ваш вес умножаем на «30» и прибавляем «500».\n" +
                "Где искать белки, жиры и углеводы?\n" +
                "   ● Рекомендуем выписать список продуктов для составления сбалансированного меню. Источниками протеина и белка для набора мышечной массы являются:\n" +
                "   ● Филе грудки курицы или индейки. Можно чередовать. Дневная норма около 200 грамм.\n" +
                "   ● Молочные продукты с пониженным содержанием жира.\n" +
                "   ● Яичные белки.\n" +
                "   ● Рыба морская.\n" +
                "   ● Растительные белки - чечевица, гречка, хлеб из цельнозерновой муки.\n" +
                "   Энергию углеводов ищем в овощах, зелени, рисе, несладких фруктах, макаронные изделия из пшеницы твердых пород, крупах. Продукты богатые полезными жирами: разнообразные орехи, семечки, скумбрия.");
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                replaceFragment(new FoodFragment());
            }
        });
        return inflatedView;
    }

    private void replaceFragment(Fragment fragment){
        FragmentManager fragmentManager = getParentFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        fragmentTransaction.replace(R.id.frame_layout, fragment);
        fragmentTransaction.commit();
    }
}