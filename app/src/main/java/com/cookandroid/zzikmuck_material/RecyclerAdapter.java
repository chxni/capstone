package com.cookandroid.zzikmuck_material;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.cookandroid.zzikmuck_material.MaterialListDB.MaterialList;
import com.cookandroid.zzikmuck_material.MaterialListDB.MaterialListDB;

import java.util.ArrayList;
import java.util.List;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.ItemViewHolder> {      //리사이클러뷰 어댑터
    // adapter에 들어갈 list 입니다.
    private List<MaterialList> materialList = new ArrayList<>();
    private Activity context;
    private MaterialListDB mlDB;

    @NonNull
    @Override
    public ItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // LayoutInflater를 이용하여 전 단계에서 만들었던 item.xml을 inflate 시킵니다.
        // return 인자는 ViewHolder 입니다.
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item, parent, false);
        return new ItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ItemViewHolder holder, int position) {
        // Item을 하나, 하나 보여주는(bind 되는) 함수입니다.

        final MaterialList data = materialList.get(position);
        mlDB = MaterialListDB.getInstance(context);
        holder.textView.setText(data.getmName());
        holder.btEdit.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                MaterialList mainData = materialList.get(holder.getAdapterPosition());

                final int sID = mainData.getId();
                String sText = mainData.getmName();

                final Dialog dialog = new Dialog(context);
                dialog.setContentView(R.layout.dialog_update);

                int width = WindowManager.LayoutParams.MATCH_PARENT;
                int height = WindowManager.LayoutParams.WRAP_CONTENT;

                dialog.getWindow().setLayout(width, height);

                dialog.show();

                final EditText editText = dialog.findViewById(R.id.dialog_edit_text);
                Button bt_update = dialog.findViewById(R.id.bt_update);

                editText.setText(sText);

                bt_update.setOnClickListener(new View.OnClickListener()
                {
                    @Override
                    public void onClick(View v)
                    {
                        dialog.dismiss();
                        String uText = editText.getText().toString().trim();

                        mlDB.materialDao().update(sID, uText);

                        materialList.clear();
                        materialList.addAll(mlDB.materialDao().getAll());
                        notifyDataSetChanged();
                    }
                });
            }
        });
        //holder.onBind(materialList.get(position));
    }

    @Override
    public int getItemCount() {
        // RecyclerView의 총 개수 입니다.
        return materialList.size();
    }

    void addItem(Data data) {
        // 외부에서 item을 추가시킬 함수입니다.
        materialList.add(data);
    }

    // RecyclerView의 핵심인 ViewHolder 입니다.
    // 여기서 subView를 setting 해줍니다.
    class ItemViewHolder extends RecyclerView.ViewHolder {

        TextView textView;
        ImageView btEdit, btDelete;

        public ItemViewHolder(@NonNull View view)
        {
            super(view);
            textView = view.findViewById(R.id.text_view);
            btEdit = view.findViewById(R.id.bt_edit);
            btDelete = view.findViewById(R.id.bt_delete);
        }

        void onBind(Data data) {
            textView.setText(data.getTitle());
        }
    }
}
