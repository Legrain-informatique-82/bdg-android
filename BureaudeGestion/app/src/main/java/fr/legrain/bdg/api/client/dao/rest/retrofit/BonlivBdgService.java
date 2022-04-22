package fr.legrain.bdg.api.client.dao.rest.retrofit;

import java.util.List;
import java.util.concurrent.ExecutionException;

import fr.legrain.bdg.MainActivity;
import fr.legrain.bdg.api.client.dao.IBonlivBdgService;
import fr.legrain.bdg.api.client.dao.rest.retrofit.interfaces.IBonlivRetrofit;
import fr.legrain.bdg.api.client.dao.rest.retrofit.tasks.RestTaskRetrofit;
import fr.legrain.bdg.api.client.dao.rest.retrofit.tasks.RestTaskRetrofitList;
import fr.legrain.bdg.api.client.dto.BonlivDTO;
import retrofit2.Call;

public class BonlivBdgService implements IBonlivBdgService {

    private RestTaskRetrofitList<BonlivDTO> t = new RestTaskRetrofitList<BonlivDTO>(MainActivity.getAppContext(),"");
    private RestTaskRetrofit<BonlivDTO> t2 = new RestTaskRetrofit<BonlivDTO>(MainActivity.getAppContext(),"");

    @Override
    public List<BonlivDTO> selectAll() {

        IBonlivRetrofit bonlivCall = RestTaskRetrofitList.getRetrofitInstance().create(IBonlivRetrofit.class);

        Call<BonlivDTO[]> call = bonlivCall.all(/*param.getDossier()*/);
        t.execute(call);

        List<BonlivDTO> l = null;
        try {
            l = t.get();
            System.out.println("BonlivBdgService.selectAll");
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        return l;
    }

    @Override
    public BonlivDTO findById(int id) {
        IBonlivRetrofit bonlivCall = RestTaskRetrofit.getRetrofitInstance().create(IBonlivRetrofit.class);

        Call<BonlivDTO> call = bonlivCall.select(/*param.getDossier()*/id);
        t2.execute(call);

        BonlivDTO l = null;
        try {
            l = t2.get();
            System.out.println("BonlivBdgService.findById");
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        return l;
    }

    @Override
    public void persist(BonlivDTO bonliv) {
        IBonlivRetrofit bonlivCall = RestTaskRetrofit.getRetrofitInstance().create(IBonlivRetrofit.class);

        Call<BonlivDTO> call = bonlivCall.create(/*param.getDossier()*/bonliv);
        t2.execute(call);

        BonlivDTO l = null;
        try {
            l = t2.get();
            System.out.println("BonlivBdgService.persist");
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        //return l;
    }

    @Override
    public void merge(BonlivDTO bonliv) {
        IBonlivRetrofit bonlivCall = RestTaskRetrofit.getRetrofitInstance().create(IBonlivRetrofit.class);

        Call<BonlivDTO> call = bonlivCall.update(/*param.getDossier()*/bonliv.getId(), bonliv);
        t2.execute(call);

        BonlivDTO l = null;
        try {
            l = t2.get();
            System.out.println("BonlivBdgService.merge");
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        //return l;
    }

    @Override
    public void remove(BonlivDTO bonliv) {
        IBonlivRetrofit bonlivCall = RestTaskRetrofit.getRetrofitInstance().create(IBonlivRetrofit.class);

        Call<BonlivDTO> call = bonlivCall.delete(/*param.getDossier()*/bonliv.getId());
        t2.execute(call);

        BonlivDTO l = null;
        try {
            l = t2.get();
            System.out.println("BonlivBdgService.remove");
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        //return l;
    }
}
