package iesm.pmdm.t4_recyclerview;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class RecyclerViewAdaptador extends RecyclerView.Adapter<RecyclerViewAdaptador.ViewHolder> {

    public static class ViewHolder extends RecyclerView.ViewHolder{

        private TextView nombre, expansion;
        ImageView imgCarta;

        public ViewHolder(View itemView){
            super(itemView);
            nombre = itemView.findViewById(R.id.nameCard);
            expansion = itemView.findViewById(R.id.expansionCard);
            imgCarta = itemView.findViewById(R.id.imageView);

        }
    }

    public List<CartaModel> cartaLista;

    public RecyclerViewAdaptador(List<CartaModel> cartaLista){
        this.cartaLista = cartaLista;
    }

    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_carta, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.nombre.setText(cartaLista.get(position).getNombre());
        holder.expansion.setText(cartaLista.get(position).getExpansion());
        holder.imgCarta.setImageResource(cartaLista.get(position).getImgCarta());
    }
    public int getItemCount(){
        return cartaLista.size();
    }

}
