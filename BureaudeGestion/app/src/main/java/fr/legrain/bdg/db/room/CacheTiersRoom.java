package fr.legrain.bdg.db.room;

import android.content.Context;
import android.widget.ProgressBar;

import java.util.List;

import androidx.room.Room;

import fr.legrain.bdg.api.client.dto.TiersDTO;
import fr.legrain.bdg.db.sqlite.BdgCRUD;
import fr.legrain.bdg.data.model.Parametre;

public class CacheTiersRoom {

    private Context context;
    private ProgressBar progressBar;

    public CacheTiersRoom(Context context) {
        this.context = context;
        this.progressBar = null;
    }

    public CacheTiersRoom(Context context, ProgressBar progressBar) {
        this.context = context;
        this.progressBar = progressBar;
    }

    public void cacheBdd(List<TiersDTO> liste) {
        BdgCRUD crud = new BdgCRUD(context/*getBaseContext()*/);
//        for (TiersDTO t : liste) {
//            crud.createTiers(t);
//        }
//        context.deleteDatabase("bdg");
//        context.deleteDatabase("bdg.db");
        AppDatabase db = Room.databaseBuilder(context,
                AppDatabase.class, Parametre.CONST_DB_NAME)
                .allowMainThreadQueries()
                .build();

        db.tiersDao().deleteTable();

        for (TiersDTO t : liste) {
            Tiers tr = TiersMapper.INSTANCE.tiersDtoToTiers(t);
            db.tiersDao().insertAll(tr);
        }

        db.close();
        // crud.exportDatabse("");
//        RestTaskRetrofitList a = new RestTaskRetrofitList(context,progressBar);
//        try {
//            a.execute(liste);
//            a.get();
//            System.out.println("TiersBdgService.selectAll");
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        } catch (ExecutionException e) {
//            e.printStackTrace();
//        }
//    }
    }
}

//class RestTaskRetrofitList extends AsyncTask<List<TiersDTO>, Void, List<TiersDTO>> {
//
//
//    private Context mContext;
//    private String mAction;
//
//    private ProgressBar progressBar;
//
//    public RestTaskRetrofitList(Context context, ProgressBar progressBar) {
//        mContext = context;
//        this.progressBar = progressBar;
//    }
//
//    protected void onPreExecute() {
//        //dialog.setMessage("Loading corresponding destinations...");
//        //dialog.show();
//        if(progressBar!=null) {
//            progressBar.setVisibility(View.VISIBLE);
//            progressBar.setIndeterminate(true);
//        }
//    }
//
//
//    @Override
//    protected List<TiersDTO> doInBackground(List<TiersDTO>... params) {
//        try {
//            List<TiersDTO> call = params[0];
//
//            BdgCRUD crud = new BdgCRUD(mContext/*getBaseContext()*/);
//
//            AppDatabase db = Room.databaseBuilder(mContext,
//                    AppDatabase.class, Parametre.CONST_DB_NAME)
//                    .allowMainThreadQueries()
//                    .build();
//
//            for (TiersDTO t : call) {
//                Tiers tr = TiersMapper.INSTANCE.tiersDtoToTiers(t);
//                db.tiersDao().insertAll(tr);
//            }
//
//            db.close();
//
//            //List<T> r = new ArrayList<>(Arrays.asList(result));
//
//            return null;
//        }
//        catch (Exception e) {
//            // TODO handle this properly
//            e.printStackTrace();
//            return null;
//        }
//    }
//
//    @Override
//    protected void onPostExecute(List<TiersDTO> result) {
//        if(progressBar!=null) {
//            progressBar.setVisibility(View.GONE);
//        }
//    }
//
//}