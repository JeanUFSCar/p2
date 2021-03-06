package jean.jeansfood.Cenarios.main

import android.content.Intent
import android.net.Uri
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import android.view.Menu
import android.view.MenuItem
import android.widget.ProgressBar
import android.widget.Toast
import jean.jeansfood.Cenarios.detalhes.Detalhes
import jean.jeansfood.R
import jean.jeansfood.entidades.Food
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity(), MainContract.View {
    override fun showDetails(food: Food) {
        val editaItem = Intent(this, Detalhes::class.java)
        editaItem.putExtra(Detalhes.FOOD, food)
        startActivity(editaItem)
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        val presenter : MainContract.Presenter = MainPresenter(this)
        presenter.onLoadList()
    }



    override fun showMessage(msg: String) {
        Toast.makeText(this, msg, Toast.LENGTH_LONG).show()
    }

    //exibe a lista
    override fun showList(food: List<Food>) {



        val adapter = FoodAdapter(food, this)
        val layoutManager = LinearLayoutManager(this)
        val dividerItemDecoration = DividerItemDecoration(this, layoutManager.orientation)

        adapter.setOnItenClickListener {indexItemClicado ->
            val editaItem = Intent(this, Detalhes::class.java)
            editaItem.putExtra(Detalhes.FOOD, food[indexItemClicado])
            startActivity(editaItem)
        }

        //recyclerview(rv)
        rvFood.adapter = adapter
        rvFood.layoutManager = LinearLayoutManager(this)
        rvFood.addItemDecoration(dividerItemDecoration)

    }

    override fun showLoading() {
        pbLoading.visibility = ProgressBar.VISIBLE
    }

    override fun hideLoading() {
        pbLoading.visibility = ProgressBar.INVISIBLE
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)

        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.action_random -> {
                val presenter : MainContract.Presenter = MainPresenter(this)
                presenter.onLoadList()
                presenter.onLoadRandom()
            }
        }
        return super.onOptionsItemSelected(item)
    }

}
