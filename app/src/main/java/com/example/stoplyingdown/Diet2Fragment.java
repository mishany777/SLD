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
 * Use the {@link Diet2Fragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Diet2Fragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public Diet2Fragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Diet2Fragment.
     */
    // TODO: Rename and change types and number of parameters
    public static Diet2Fragment newInstance(String param1, String param2) {
        Diet2Fragment fragment = new Diet2Fragment();
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
        View inflatedView = inflater.inflate(R.layout.fragment_diet2, container, false);
        TextView back = inflatedView.findViewById(R.id.back_button);
        TextView dietText = inflatedView.findViewById(R.id.dietText);
        TextView title = inflatedView.findViewById(R.id.title);
        title.setText("Средиземноморская диета");
        dietText.setText("  Изучив состав потребляемых продуктов, диетологи сформулировали принципы средиземноморской системы питания, вот некоторые из них:\n" +
                "   1. 10% белков, 30% жиров и 60% сложных углеводов (крупы, бобовые, мучные изделия из твердых сортов пшеницы, фрукты, ягоды и др.).\n" +
                "   2. Нет скрупулезного подсчета калорий и взвешивания порций готовой пищи.\n" +
                "   3. Ежедневно в меню включается не меньше пяти порций овощей и фруктов, размер порции – горсть ладони.\n" +
                "   4. Овощи и фрукты должны быть разных цветов, чтобы в организм поступало как можно больше разных полезных веществ.\n" +
                "   5. Морепродукты и рыба должны быть в меню не менее двух раз в неделю.\n" +
                "   6. Источником жиров должны быть в основном растительные, а не животные масла, причем изготовленные по технологии первого или холодного отжима. Сырье при такой технологии не нагревается и не измельчается, а сразу поступает под пресс.\n" +
                "   7. Для приготовления еды используются только свежие сезонные продукты, еда готовится на один день и не остается для разогрева назавтра.\n" +
                "   8. Во время еды на столе всегда красное сухое или полусухое вино.\n" +
                "   9. Ограничено употребление красного мяса: говядины, свинины и баранины.\n" +
                "   Конечно, в разных средиземноморских странах привычные блюда могут сильно отличаться друг от друга, но общие принципы остаются неизменными – это преобладание в рационе растительной пищи, нежирной птицы и рыбы, а также растительных масел с ненасыщенными жирами. Рецепты блюд легко адаптируются для веганов или вегетарианцев.");
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