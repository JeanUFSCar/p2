package jean.jeansfood.Cenarios.main

import jean.jeansfood.entidades.FoodList
import jean.jeansfood.network.RetrofitInicializer
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainPresenter(val  view: MainContract.View) : MainContract.Presenter {


    override fun onLoadList(){

        view.showLoading()

        //retrofit iniciado
        val foodServices = RetrofitInicializer().createFoodServices()


        val call = foodServices.getMaisRescente()

        //para acessar url
        call.enqueue(object: Callback<FoodList> {
            override fun onFailure(call: Call<FoodList>, t: Throwable) {
                view.hideLoading()
                view.showMessage("Erro na conexão. Verifique sua conexão.")
            }

            override fun onResponse(call: Call<FoodList>, response: Response<FoodList>) {
                view.hideLoading()
                if (response.body() != null) {
                    view.showList(response.body()!!.meals) //exibe lista
                } else {
                    view.showMessage("não há comidas")
                }
            }
        })


    }

    override fun onLoadRandom() {
        view.showLoading()

        val foodServices = RetrofitInicializer().createFoodServices()

        val call = foodServices.getAleatoria()

        call.enqueue(object: Callback<FoodList> {
            override fun onFailure(call: Call<FoodList>, t: Throwable) {
                view.hideLoading()
                view.showMessage("Erro na conexão. Verifique sua conexão.")
            }

            override fun onResponse(call: Call<FoodList>, response: Response<FoodList>) {
                view.hideLoading()
                if (response.body() != null) {
                    view.showDetails(response.body()!!.meals[0]) //exibe lista
                } else {
                    view.showMessage("não há comidas")
                }
            }
        })
    }
}