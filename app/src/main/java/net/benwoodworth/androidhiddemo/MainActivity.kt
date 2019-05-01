package net.benwoodworth.androidhiddemo

import android.content.Context
import android.os.Bundle
import android.util.AttributeSet
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private lateinit var hidGadgets: HidGadgets

    private val settings: Settings by lazy {
        Settings.newInstance(hidGadgets)
    }

    private val keyboard: Keyboard by lazy {
        Keyboard.newInstance(hidGadgets)
    }

    private val onNavigationItemSelectedListener =
        BottomNavigationView.OnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.navigation_gadgets -> {
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.container, settings)
                        .commit()

                    return@OnNavigationItemSelectedListener true
                }
                R.id.navigation_keyboard -> {
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.container, keyboard)
                        .commit()

                    return@OnNavigationItemSelectedListener true
                }
                R.id.navigation_gamepad -> {
                    return@OnNavigationItemSelectedListener true
                }
            }
            false
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)
        val navView: BottomNavigationView = findViewById(R.id.nav_view)

        navView.setOnNavigationItemSelectedListener(onNavigationItemSelectedListener)

        navView.selectedItemId = R.id.navigation_gadgets
    }

    override fun onCreateView(name: String, context: Context, attrs: AttributeSet): View? {
        hidGadgets = ViewModelProviders.of(this).get(HidGadgets::class.java)

        hidGadgets.keyboard.observe(this, Observer { keyboard ->
            val menuItem = nav_view.menu.findItem(R.id.navigation_keyboard)
            menuItem.isEnabled = keyboard != null

            if (!menuItem.isEnabled && nav_view.selectedItemId == menuItem.itemId) {
                nav_view.selectedItemId = R.id.navigation_gadgets
            }
        })

        hidGadgets.gamePad.observe(this, Observer { gamePad ->
            val menuItem = nav_view.menu.findItem(R.id.navigation_gamepad)
            menuItem.isEnabled = gamePad != null

            if (!menuItem.isEnabled && nav_view.selectedItemId == menuItem.itemId) {
                nav_view.selectedItemId = R.id.navigation_gadgets
            }
        })

        return super.onCreateView(name, context, attrs)
    }
}
