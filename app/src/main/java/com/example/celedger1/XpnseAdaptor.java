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

public class XpnseAdaptor extends RecyclerView.Adapter<XpnseAdaptor.XpnseViewHolder> {

    //DECLARATIONS
    public String xp_cat;
    public Float xp_amt;
    public String xp_pm;
    public String xp_dte;
    public static final String TAG = "XpnseAdaptor";

    private Context xpContext;
    private Cursor xpCursor;

    public XpnseAdaptor(Context context, Cursor cursor)
    {
        xpContext = context;
        xpCursor = cursor;
    }

    //VIEW HOLDER
    @NonNull
    @Override
    public XpnseViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater Inflater = LayoutInflater.from(viewGroup.getContext());
        View view = Inflater.inflate(R.layout.xpense_list_layout, viewGroup, false);
        return new XpnseViewHolder(view);
    }

    //BIND THE VIEW TOGETHER
    @Override
    public void onBindViewHolder(@NonNull XpnseViewHolder xpnseViewHolder, int i) {
        if(!xpCursor.moveToPosition(i)){
            return;
        }

        xp_cat = xpCursor.getString(xpCursor.getColumnIndex(CeledgerContract.XpenseEntry.CATEGORY));
        xp_amt = xpCursor.getFloat(xpCursor.getColumnIndex(CeledgerContract.XpenseEntry.AMOUNT));
        xp_pm = xpCursor.getString(xpCursor.getColumnIndex(CeledgerContract.XpenseEntry.PAYMENTMETHOD));
        xp_dte = xpCursor.getString(xpCursor.getColumnIndex(CeledgerContract.XpenseEntry.DATE));
        final String desc = xpCursor.getString(xpCursor.getColumnIndex(CeledgerContract.XpenseEntry.DESCRIPTION));


        if(xp_cat.equals("Food")) {
            xpnseViewHolder.imgicon.setImageResource(R.drawable.burger_3); }
        else if(xp_cat.equals("Fees")) {
            xpnseViewHolder.imgicon.setImageResource(R.drawable.fee_3); }
        else if(xp_cat.equals("Bills")){
            xpnseViewHolder.imgicon.setImageResource(R.drawable.bill_3); }
        else if(xp_cat.equals("Shopping")){
            xpnseViewHolder.imgicon.setImageResource(R.drawable.shopping_cart_3); }
        else if(xp_cat.equals("Rent")){
            xpnseViewHolder.imgicon.setImageResource(R.drawable.house_3); }
        else if(xp_cat.equals("Travel")){
            xpnseViewHolder.imgicon.setImageResource(R.drawable.airplane_3); }
        else{
            xpnseViewHolder.imgicon.setImageResource(R.drawable.wallet_3); }

        xpnseViewHolder.Xpnsetitle.setText(xp_cat);
        xpnseViewHolder.xp_amt.setText(String.valueOf(xp_amt));
        xpnseViewHolder.xp_pm.setText(xp_pm);
        xpnseViewHolder.xp_dte.setText(xp_dte);

        xpnseViewHolder.xpnsell.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent xpdesc = new Intent(xpContext, DetailView.class);
                xpdesc.putExtra("category",xp_cat);
                xpdesc.putExtra("amount",String.valueOf(xp_amt));
                xpdesc.putExtra("Paymethod",xp_pm);
                xpdesc.putExtra("date",xp_dte);
                xpdesc.putExtra("description",desc);
                xpContext.startActivity(xpdesc);
            }
        });
    }


    //NUMBER OF ITEMS TO BE SHOWN IN THE VIEW
    @Override
    public int getItemCount()  {
            return xpCursor.getCount();
    }

    public void swapxpCursor(Cursor newCursor){
        if(xpCursor != null){
            xpCursor.close();
        }
        xpCursor = newCursor;
        if(newCursor != null){
            notifyDataSetChanged();
        }
    }

    //CREATE A VIEW
    public class XpnseViewHolder extends RecyclerView.ViewHolder{
        LinearLayout xpnsell;
        ImageView imgicon;
        TextView Xpnsetitle;
        TextView xp_amt;
        TextView xp_pm;
        TextView xp_dte;
        public XpnseViewHolder(@NonNull View itemView) {
            super(itemView);
            xpnsell = itemView.findViewById(R.id.xpnsell);
            imgicon = itemView.findViewById(R.id.imgicon);
            Xpnsetitle = itemView.findViewById(R.id.Xpnsetitle);
            xp_amt = itemView.findViewById(R.id.xp_amt);
            xp_pm = itemView.findViewById(R.id.xpnsepm);
            xp_dte = itemView.findViewById(R.id.xpnsedte);
        }
    }

}
