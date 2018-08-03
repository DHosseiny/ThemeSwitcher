package ir.parsdroid.themeapp

import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.design.widget.FloatingActionButton
import android.support.design.widget.Snackbar
import android.support.v4.view.GravityCompat
import android.support.v7.app.ActionBarDrawerToggle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import ir.parsdroid.themeapp.databinding.ActivityMainBinding
import ir.parsdroid.themeapp.theme.InteractionListener
import ir.parsdroid.themeapp.theme.ThemeFragment


class MainActivity : AppCompatActivity(), InteractionListener {

    private lateinit var binding: ActivityMainBinding


/*    private val onThemeChangedCallback = object : Observable.OnPropertyChangedCallback() {
        override fun onPropertyChanged(sender: Observable?, propertyId: Int) {

            if (propertyId == BR._all) {
                ThemeAgent.changeStatusBarColor(this@MainActivity)
            }
        }
    }*/

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        ThemeAgent.checkTheme(this)
//        Theme.getInstance().addOnPropertyChangedCallback(onThemeChangedCallback)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        binding.appBarMain.theme = Theme.getInstance()

        setupView()

        val fragment = ThemeFragment()
        supportFragmentManager.beginTransaction()
                .add(R.id.container, fragment)
                .commit()
    }

    private fun setupView() {
        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)

        val fab = findViewById<FloatingActionButton>(R.id.fab)
        fab.setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show()
        }

        val toggle = ActionBarDrawerToggle(
                this, binding.drawerLayout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close)
        binding.drawerLayout.addDrawerListener(toggle)
        toggle.syncState()
    }

    override fun onBackPressed() {
        if (binding.drawerLayout.isDrawerOpen(GravityCompat.START)) {
            binding.drawerLayout.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }

//    override fun onCreateOptionsMenu(menu: Menu): Boolean {
//        // Inflate the menu; this adds items to the action bar if it is present.
//        menuInflater.inflate(R.menu.main, menu)
//        return true
//    }
//
//    override fun onOptionsItemSelected(item: MenuItem): Boolean {
//        // Handle action bar item clicks here. The action bar will
//        // automatically handle clicks on the Home/Up button, so long
//        // as you specify a parent activity in AndroidManifest.xml.
//        val id = item.itemId
//
//
//        return if (id == R.id.action_settings) {
//            true
//        } else super.onOptionsItemSelected(item)
//
//    }


    override fun onThemeSelected(theme: ThemeInfo) {
        //TODO
    }
}
