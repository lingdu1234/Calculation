package top.iu1314.calculation

import android.app.AlertDialog
import android.content.DialogInterface
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.SavedStateViewModelFactory
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.ui.NavigationUI


/*
class MainActivity : AppCompatActivity() {
    lateinit var navController: NavController
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        navController = Navigation.findNavController(this, R.id.fragment)
        NavigationUI.setupActionBarWithNavController(this, navController)

    }

    override fun onSupportNavigateUp(): Boolean {
        if (navController.currentDestination?.id == R.id.questionFragment) {
            val builder: AlertDialog.Builder = AlertDialog.Builder(this)
            builder.setTitle(R.string.quit_dialog_title)
            builder.setPositiveButton(
                R.string.quit_dialog_positive_message
            ) { _, _ -> navController.navigateUp() }
*/
/*            builder.setPositiveButton(R.string.quit_dialog_positive_message,object :
                DialogInterface.OnClickListener {
                override fun onClick(dialog: DialogInterface?, which: Int) {
                    navController.navigateUp()
                }

            })*//*

            builder.setNegativeButton(
                R.string.quit_dialog_negative_message
            ) { _, _ -> }
            val dialog = builder.create()
            dialog.show()

        } else {
            navController.navigate(R.id.titleFragment)
        }
        return super.onSupportNavigateUp()

    }

    override fun onBackPressed() {
        onSupportNavigateUp()
    }
}
*/
class MainActivity : AppCompatActivity() {
    private lateinit var controller: NavController
    private lateinit var myViewModel: MyViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

//        myViewModel = ViewModelProvider(this)[MyViewModel::class.java]
        myViewModel = ViewModelProvider(
            this,
            SavedStateViewModelFactory(application, this)
        ).get(MyViewModel::class.java)
        controller = Navigation.findNavController(this, R.id.fragment)
        NavigationUI.setupActionBarWithNavController(this, controller)
    }

    override fun onSupportNavigateUp(): Boolean {
        when (controller.currentDestination!!.id) {
            R.id.questionFragment -> {
                val builder = AlertDialog.Builder(this)
                builder.setTitle(getString(R.string.quit_dialog_title))
                builder.setPositiveButton(R.string.quit_dialog_positive_message,
                    DialogInterface.OnClickListener { _, _ ->
                        myViewModel.currentScore.value = 0
                        controller.navigateUp()
                    })
                builder.setNegativeButton(R.string.quit_dialog_negative_message,
                    DialogInterface.OnClickListener { _, _ -> })
                val dialog = builder.create()
                dialog.show()
            }
            R.id.titleFragment -> {
                finish()
            }
            else -> {
                controller.navigate(R.id.titleFragment)
            }
        }
        return super.onSupportNavigateUp()
    }

    override fun onBackPressed() {
        onSupportNavigateUp()
    }
}