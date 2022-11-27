package com.cookandroid.zzikmuck_material;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.cookandroid.zzikmuck_material.MaterialListDB.MaterialList;
import com.cookandroid.zzikmuck_material.MaterialListDB.MaterialListDB;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Fragment2#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Fragment2 extends Fragment {

    private RecyclerAdapter adapter;
    private RecyclerView recyclerView;
    private List<MaterialList> materialList = new ArrayList<>();

    EditText editText;
    Button btAdd;

    MaterialListDB mlDB;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public Fragment2() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Fragment2.
     */
    // TODO: Rename and change types and number of parameters
    public static Fragment2 newInstance(String param1, String param2) {
        Fragment2 fragment = new Fragment2();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        /*if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
        */
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v =  inflater.inflate(R.layout.fragment_2, container, false);

        init(v);

        getData();

        editText = v.findViewById(R.id.edit_text);
        btAdd = v.findViewById(R.id.bt_add);
        recyclerView = v.findViewById(R.id.recycler_view);

        mlDB = MaterialListDB.getInstance(container.getContext());

        materialList = mlDB.materialDao().getAll();

        recyclerView.setLayoutManager(new LinearLayoutManager(container.getContext()));

        adapter = new RecyclerAdapter(Fragment2.this, materialList);

        recyclerView.setAdapter(adapter);

        btAdd.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                String sText = editText.getText().toString().trim();
                if (!sText.equals(""))
                {
                    MaterialList data = new MaterialList();
                    data.setmName(sText);
                    mlDB.materialDao().setInsertMatList(data);

                    editText.setText("");

                    materialList.clear();
                    materialList.addAll(mlDB.materialDao().getAll());
                    adapter.notifyDataSetChanged();
                }
            }
        });

        return v;
    }

    private void init(View v) {
        recyclerView = v.findViewById (R.id.recyclerView);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(linearLayoutManager);

        adapter = new RecyclerAdapter();
        recyclerView.setAdapter(adapter);
    }

    private void getData() {
        List<String> listTitle = Arrays.asList("소고기", "돼지고기", "닭고기", "새우", "게", "오징어", "고등어", "우유",
                "땅콩", "호두", "잣", "대두", "복숭아", "토마토", "밀", "메밀");

        for (int i = 0; i < listTitle.size(); i++) {
            // 각 List의 값들을 data 객체에 set 해줍니다.
            Data data = new Data();
            data.setTitle(listTitle.get(i));

            // 각 값이 들어간 data를 adapter에 추가합니다.
            adapter.addItem(data);
        }

        // adapter의 값이 변경되었다는 것을 알려줍니다.
        adapter.notifyDataSetChanged();
    }
}