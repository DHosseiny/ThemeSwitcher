/*
 * Created by DHosseiny. ThemeSwitcher project.
 */

package davud.hosseini.themeapp

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.view.GravityCompat
import androidx.databinding.DataBindingUtil
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar
import davud.hosseini.themeapp.databinding.ActivityMainBinding
import davud.hosseini.themeswitcher.DataBindingThemeSwitcher
import davud.hosseini.themeswitcher.core.theme.Theme
import davud.hosseini.themeswitcher.core.theme.ThemeSwitcher
import java.io.File


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding


    private fun onThemeChanged(theme: Theme) {
        Toast.makeText(this, "Theme changed to ${theme.themeInfo.name}", Toast.LENGTH_LONG).show()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val file = File(filesDir, "testUserTheme.xml")
        if (!file.exists()) {
            file.writeText("<resources>\n" +
                    "    <color name=\"colorPrimary\">#FFFFFFFF</color>\n" +
                    "    <color name=\"colorPrimaryDark\">#FFF0F0F0</color><!--002984-->\n" +
                    "    <color name=\"colorAccent\">#FF4081</color>\n" +
                    "</resources>\n")
        }

        DataBindingThemeSwitcher.setup(this)
        ThemeSwitcher.addThemeChangeListener(::onThemeChanged)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

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

        val toggle = ActionBarDrawerToggle(this, binding.drawerLayout, toolbar,
                R.string.navigation_drawer_open,
                R.string.navigation_drawer_close)
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
}