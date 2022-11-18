package com.example.myfinalapp

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.widget.Button
import android.widget.ImageButton
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.myfinalapp.databinding.ActivitySecondScreenBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class SecondScreen : AppCompatActivity() {

    private lateinit var binding: ActivitySecondScreenBinding
    private lateinit var firebaseAuth: FirebaseAuth
    private lateinit var personArrayList: ArrayList<Person>
    private lateinit var database: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySecondScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)


        val imageID = intArrayOf(

            R.drawable.a, R.drawable.b, R.drawable.c, R.drawable.d, R.drawable.e, R.drawable.f,
        )
        val checkbox = intArrayOf(
            R.drawable.ic_checkbox,
            R.drawable.ic_checkbox,
            R.drawable.ic_checkbox,
            R.drawable.ic_checkbox,
            R.drawable.ic_checkbox,
            R.drawable.ic_checkbox
        )
        val person_icon = intArrayOf(
            R.drawable.ic_person,
            R.drawable.ic_person,
            R.drawable.ic_person,
            R.drawable.ic_person,
            R.drawable.ic_person,
            R.drawable.ic_person
        )

        val activities = arrayOf(
            "Box",
            "Running",
            "Calisthenics",
            "Power Lifting",
            "Yoga",
            "Stretching"
        )

        val name = arrayOf(
            "Tommie Mitchell",
            "Shawn West",
            "Sadie Haley",
            "Robert Schuppe",
            "Rachel Haley",
            "Clara Bruen"
        )

        val activityTime = arrayOf(
            "13:00",
            "15:00",
            "16:00",
            "18:30",
            "21:15",
            "23:00"
        )

        val description = arrayOf(
            "Boxing is a combat sport in which two people, usually wearing protective gloves and other protective equipment such as hand wraps and mouthguards, throw punches at each other for a predetermined amount of time in a boxing ring.",
            "Running, footracing over a variety of distances and courses and numbering among the most popular sports in nearly all times and places. Modern competitive running ranges from sprints (dashes), with their emphasis on continuous high speed, to grueling long-distance and marathon races, requiring great endurance.",
            "Calisthenics is a type of workout that uses a person's body weight with little or no equipment. The exercises involve movements that use large muscle groups, such as pushups. People typically perform these exercises at a moderate pace. They help to improve coordination, flexibility, and strength.",
            "Powerlifting is a strength sport that consists of three attempts at maximal weight on three lifts: squat, bench press, and deadlift.",
            "Yoga is an ancient practice that involves physical poses, concentration, and deep breathing. A regular yoga practice can promote endurance, strength, calmness, flexibility, and well-being.",
            "Stretching is a form of physical exercise in which a specific muscle or tendon is deliberately flexed or stretched in order to improve the muscle's felt elasticity and achieve comfortable muscle tone. The result is a feeling of increased muscle control, flexibility, and range of motion."
        )
        personArrayList = ArrayList()

        for (i in activities.indices) {

            val person = Person(
                activities[i],
                name[i],
                activityTime[i],
                description[i],
                imageID[i],
                checkbox[i],
                person_icon[i]
            )
            personArrayList.add(person)
        }
        binding.listview.adapter = MyAdapter(this, personArrayList)
        binding.listview.isClickable = true
        binding.listview.setOnItemClickListener { parent, view, position, id ->

            val name = name[position]
            val activities = activities[position]
            val activityTime = activityTime[position]
            val description = description[position]
            val imageID = imageID[position]
            val i = Intent(this, DescriptionActivity::class.java)
            val e = Intent(this, ProfileScreen::class.java)
            i.putExtra("name", name)
            i.putExtra("activityTime", activityTime)
            e.putExtra("activities", activities)
            i.putExtra("imageID", imageID)
            i.putExtra("description", description)
            startActivity(i)
        }
//            val uid = firebaseAuth.currentUser?.uid

            firebaseAuth = FirebaseAuth.getInstance()
//            database = FirebaseDatabase.getInstance().getReference("Users")




            val profileicon = findViewById<ImageView>(R.id.profile_icon)
            profileicon.setOnClickListener {
                startActivity(Intent(this, ProfileScreen::class.java))
            }
            val menuicon = findViewById<ImageView>(R.id.menu_icon)
            menuicon.setOnClickListener {
                startActivity(Intent(this, MenuScreen::class.java))
            }

        }
    }