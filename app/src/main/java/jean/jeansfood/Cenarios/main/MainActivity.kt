package jean.jeansfood.Cenarios.main

import android.content.Intent
import android.net.Uri
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.widget.Toast
import jean.jeansfood.R
import jean.jeansfood.entidades.Food

class MainActivity : AppCompatActivity(), MainContract.View {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val presenter : MainContract.Presenter = MainPresenter(this)
        presenter.onLoadList()
    }



    override fun showMessage(msg: String) {
        Toast.makeText(this, msg, Toast.LENGTH_LONG).show()
    }

    override fun showList(food: List<Food>) {

//        val adapter = FoodAdapter(this, Food)
//        adapter.setOnItemClickListener { position ->
//            val openBrowser = Intent(Intent.ACTION_VIEW)
//            openBrowser.data = Uri.parse(food.get(position).url)
//            startActivity(OpenBrowser)
//        }
//
//        rvFood.adapter = adapter
//        rvFood.layoutManager = LinearLayoutManager(this)


    }


}
