package com.example.myapplication;
import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;
public class FacturaAdapterClass extends RecyclerView.Adapter<FacturaAdapterClass.ViewHolder> {

    List<FacturaModelClass> factura;
    Context context;
    Database databaseHelperClass;

    public FacturaAdapterClass(List<FacturaModelClass> factura, Context context) {
        this.factura = factura;
        this.context = context;
        databaseHelperClass = new Database(context);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.facturi_item_list,parent,false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, final int position) {
        final FacturaModelClass facturaModelClass = factura.get(position);

        holder.textViewID.setText(Integer.toString(facturaModelClass.getId()));
        holder.edittext_Societate.setText(facturaModelClass.getSocietate());
        holder.edittext_Facturant.setText(facturaModelClass.getFacturant());
        holder.edittext_Serie.setText(facturaModelClass.getSerie());
        holder.edittext_Pret.setText(facturaModelClass.getPret());


        holder.button_Edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String stringSocietate = holder.edittext_Societate.getText().toString();
                String stringFacturant = holder.edittext_Facturant.getText().toString();
                String stringSerie = holder.edittext_Facturant.getText().toString();
                String stringPret = holder.edittext_Facturant.getText().toString();

                databaseHelperClass.updateFactura(new FacturaModelClass(facturaModelClass.getId(),stringSocietate,stringFacturant,stringSerie,stringPret));
                notifyDataSetChanged();
                ((Activity) context).finish();
                context.startActivity(((Activity) context).getIntent());
            }
        });

        holder.button_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                databaseHelperClass.deleteFactura(facturaModelClass.getId());
                factura.remove(position);
                notifyDataSetChanged();
            }
        });

    }

    @Override
    public int getItemCount() {
        return factura.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView textViewID;
        EditText edittext_Societate;
        EditText edittext_Facturant;
        EditText edittext_Serie;
        EditText edittext_Pret;
        Button button_Edit;
        Button button_delete;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            textViewID = itemView.findViewById(R.id.text_id);
            edittext_Societate = itemView.findViewById(R.id.edittext_societate);
            edittext_Facturant = itemView.findViewById(R.id.edittext_facturant);
            edittext_Serie = itemView.findViewById(R.id.edittext_serie);
            edittext_Pret = itemView.findViewById(R.id.edittext_pret);
            button_delete = itemView.findViewById(R.id.button_delete);
            button_Edit = itemView.findViewById(R.id.button_edit);

        }
    }
}

