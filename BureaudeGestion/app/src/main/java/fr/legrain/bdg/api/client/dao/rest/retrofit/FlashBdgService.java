package fr.legrain.bdg.api.client.dao.rest.retrofit;

import java.util.List;
import java.util.concurrent.ExecutionException;

import fr.legrain.bdg.MainActivity;
import fr.legrain.bdg.api.client.dao.IFlashBdgService;
import fr.legrain.bdg.api.client.dao.rest.retrofit.interfaces.IFlashRetrofit;
import fr.legrain.bdg.api.client.dao.rest.retrofit.tasks.RestTaskRetrofit;
import fr.legrain.bdg.api.client.dao.rest.retrofit.tasks.RestTaskRetrofitList;
import fr.legrain.bdg.api.client.dto.FlashDTO;
import retrofit2.Call;

public class FlashBdgService implements IFlashBdgService {

    private RestTaskRetrofitList<FlashDTO> t = new RestTaskRetrofitList<FlashDTO>(MainActivity.getAppContext(),"");
    private RestTaskRetrofit<FlashDTO> t2 = new RestTaskRetrofit<FlashDTO>(MainActivity.getAppContext(),"");

    @Override
    public List<FlashDTO> selectAll() {

        IFlashRetrofit flashCall = RestTaskRetrofitList.getRetrofitInstance().create(IFlashRetrofit.class);

        Call<FlashDTO[]> call = flashCall.all(/*param.getDossier()*/);
        t.execute(call);

        List<FlashDTO> l = null;
        try {
            l = t.get();
            System.out.println("FlashBdgService.selectAll");
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        return l;
    }

    public List<FlashDTO> selectAll(String codeTypeDocument) {
        IFlashRetrofit flashCall = RestTaskRetrofitList.getRetrofitInstance().create(IFlashRetrofit.class);

        Call<FlashDTO[]> call = flashCall.all(codeTypeDocument);
        t.execute(call);

        List<FlashDTO> l = null;
        try {
            l = t.get();
            System.out.println("FlashBdgService.selectAll");
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        return l;
    }

    @Override
    public FlashDTO findById(int id) {
        IFlashRetrofit flashCall = RestTaskRetrofit.getRetrofitInstance().create(IFlashRetrofit.class);

        Call<FlashDTO> call = flashCall.select(/*param.getDossier()*/id);
        t2.execute(call);

        FlashDTO l = null;
        try {
            l = t2.get();
            System.out.println("FlashBdgService.findById");
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        return l;
    }

    @Override
    public FlashDTO persist(FlashDTO bonliv) {
        IFlashRetrofit flashCall = RestTaskRetrofit.getRetrofitInstance().create(IFlashRetrofit.class);

        Call<FlashDTO> call = flashCall.create(/*param.getDossier()*/bonliv);
        t2.execute(call);

        FlashDTO l = null;
        try {
            l = t2.get();
            System.out.println("FlashBdgService.persist");
            return l;
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public FlashDTO merge(FlashDTO bonliv) {
        IFlashRetrofit flashCall = RestTaskRetrofit.getRetrofitInstance().create(IFlashRetrofit.class);

        Call<FlashDTO> call = flashCall.update(/*param.getDossier()*/bonliv.getId(), bonliv);
        t2.execute(call);

        FlashDTO l = null;
        try {
            l = t2.get();
            System.out.println("FlashBdgService.merge");
            return l;
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void remove(FlashDTO bonliv) {
        IFlashRetrofit flashCall = RestTaskRetrofit.getRetrofitInstance().create(IFlashRetrofit.class);

        Call<FlashDTO> call = flashCall.delete(/*param.getDossier()*/bonliv.getId());
        t2.execute(call);

        FlashDTO l = null;
        try {
            l = t2.get();
            System.out.println("FlashBdgService.remove");
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        //return l;
    }
}
