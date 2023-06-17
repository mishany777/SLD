package com.example.stoplyingdown;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Diet1Fragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Diet1Fragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public Diet1Fragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Diet1Fragment.
     */
    // TODO: Rename and change types and number of parameters
    public static Diet1Fragment newInstance(String param1, String param2) {
        Diet1Fragment fragment = new Diet1Fragment();
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
        View inflatedView = inflater.inflate(R.layout.fragment_diet1, container, false);
        TextView back = inflatedView.findViewById(R.id.back_button);
        TextView dietText = inflatedView.findViewById(R.id.dietText);
        TextView title = inflatedView.findViewById(R.id.title);
        title.setText("Диета Аткинса");
        dietText.setText("  Сокращается до минимума употребление хлеба, макаронных изделий, риса, фасоли и картофеля. Основная еда: белки – рыба, говядина, курица и свинина, от 110 до 170 г за один прием пищи (в оригинале 4-6 унций). В каждый прием пищи разрешена порция заправки для салата, сливочного или оливкового масла. Остальное пространство тарелки нужно заполнить низкоуглеводными некрахмалистыми овощами-основой: шпинатом, рукколой, брокколи, огурцами, помидорами, сельдереем или брюссельской капустой.\n" +
                "   Для расчета меню Аткинс ввел понятие чистых углеводов» – общее количество углеводов в том или ином продукте за вычетом клетчатки.\n" +
                "   Сейчас известно три типа диеты, в зависимости от разрешенного количества (в граммах) «чистых углеводов»: диета Аткинса 20, диета Аткинса 40 и диета 100. Первые две разновидности состоят из четырех фаз и рассчитаны на похудение, а диета Аткинса 100 – это система питания на всю жизнь.\n" +
                "   Классикой является самая строгая диета 20: \n" +
                "   ● 1-я фаза– индукция, длительностью две недели, она самая строгая, когда практически исключаются все углеводы, кроме 12-15 г «чистых углеводов» в день, содержащихся в разрешенных овощах. Белковая пища преобладает. Исключены практически все фрукты, выпечка, макароны, крупы и орехи. Пить нужно примерно 8 стаканов воды в сутки. Автор оценивал примерную потерю веса на этой фазе около 7 кг.\n" +
                "   ● 2-я фаза – основная. Разрешается больше овощей, фруктов, семечек и орехов, начиная с ежедневных 25 г чистых углеводов, постепенно добавляя по 5 г (максимум 80 г). Продолжать эту фазу нужно до тех пор, пока не потеряете примерно 4,5 кг от исходного веса.\n" +
                "   ● 3-я фаза – переходная. Можно добавлять еще по 5-10 г «чистых углеводов», но, если вес перестанет уменьшаться, надо вернуться в предыдущей фазе, пока не достигнете запланированного похудения. В конце этапа потребление углеводов составляет уже 80-100 г в день.\n" +
                "   ● 4-я фаза – пожизненная. Питание такими продуктами, которые обеспечивают сохранение достигнутого веса (это от 40 до 120 г чистых углеводов ежедневно). Если вес опять начинает расти, то углеводы приходится опять сокращать!");
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