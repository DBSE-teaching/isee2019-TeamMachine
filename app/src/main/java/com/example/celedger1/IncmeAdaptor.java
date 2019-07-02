package com.example.celedger1;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

//CLASS FOR INCOME ADAPTOR
public class IncmeAdaptor extends RecyclerView.Adapter<IncmeAdaptor.IncmeViewHolder> {

    //DECLARATIONS
    public String ic_cat;
    public Float ic_amt;
    public String ic_pm;
    public String ic_dte;

    private Context icContext;
    private Cursor icCursor;

    public IncmeAdaptor(Context context, Cursor cursor)
    {
        icContext = context;
        icCursor = cursor;
    }


    //VIEW HOLDER
    @NonNull
    @Override
    public IncmeViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater Inflater = LayoutInflater.from(viewGroup.getContext());
        View view = Inflater.inflate(R.layout.income_list_layout, viewGroup, false);
        return new IncmeViewHolder(view);
    }

    //BIND THE VIEW TOGETHER
    @Override
    public void onBindViewHolder(@NonNull IncmeViewHolder incmeViewHolder, int i) {
        if(!icCursor.moveToPosition(i)){
            return;
        }

        ic_cat = icCursor.getString(icCursor.getColumnIndex(CeledgerContract.IncomeEntry.COL_4));
        ic_amt = icCursor.getFloat(icCursor.getColumnIndex(CeledgerContract.IncomeEntry.COL_5));
        ic_pm = icCursor.getString(icCursor.getColumnIndex(CeledgerContract.IncomeEntry.COL_3));
        ic_dte = icCursor.getString(icCursor.getColumnIndex(CeledgerContract.IncomeEntry.COL_2));
        final String desc = icCursor.getString(icCursor.getColumnIndex(CeledgerContract.IncomeEntry.COL_7));

        if(ic_cat.equals("Payment")){
            incmeViewHolder.imgicon.setImageResource(R.drawable.payment_3); }
        else if(ic_cat.equals("Salary")){
            incmeViewHolder.imgicon.setImageResource(R.drawable.coins_3); }
        else {
            incmeViewHolder.imgicon.setImageResource(R.drawable.wallet_3); }

        incmeViewHolder.Incmetitle.setText(ic_cat);
        incmeViewHolder.ic_amt.setText(String.valueOf(ic_amt));
        incmeViewHolder.ic_pm.setText(ic_pm);
        incmeViewHolder.ic_dte.setText(ic_dte);

        incmeViewHolder.incmell.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent icdesc = new Intent(icContext, IncDetailView.class);
                icdesc.putExtra("icategory",ic_cat);
                icdesc.putExtra("iamount",String.valueOf(ic_amt));
                icdesc.putExtra("iPaymethod",ic_pm);
                icdesc.putExtra("idate",ic_dte);
                icdesc.putExtra("idescription",desc);
                icContext.startActivity(icdesc);
            }
        });
    }

    //NUMBER OF ITEMS TO BE SHOWN IN THE VIEW
    @Override
    public int getItemCount() {
        return icCursor.getCount();
    }

    public void swapicCursor(Cursor newCursor){
        if(icCursor != null){
            icCursor.close();
        }
        icCursor = newCursor;
        if(newCursor != null){
            notifyDataSetChanged();
        }
    }

    //CREATE A VIEW
    public class IncmeViewHolder extends RecyclerView.ViewHolder{
        LinearLayout incmell;
        ImageView imgicon;
        TextView Incmetitle;
        TextView ic_pm;
        TextView ic_amt;
        TextView ic_dte;
        public IncmeViewHolder(@NonNull View itemView) {
            super(itemView);
            incmell = itemView.findViewById(R.id.incmell);
            imgicon = itemView.findViewById(R.id.imgicon);
            Incmetitle = itemView.findViewById(R.id.Incmetitle);
            ic_amt = itemView.findViewById(R.id.ic_amt);
            ic_pm = itemView.findViewById(R.id.Incmepm);
            ic_dte = itemView.findViewById(R.id.Incmedte);
        }
    }

}
