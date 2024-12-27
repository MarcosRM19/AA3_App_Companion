package com.example.aa3_app_companion

import android.content.Intent
import android.os.Bundle
import android.widget.ImageButton
import android.widget.ImageView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import models.Armor
import models.Attributes
import models.DamageNegation
import models.Magic
import models.Talisman

class ItemActivity : AppCompatActivity() {

    private lateinit var classesButton: ImageButton
    private lateinit var bossButton: ImageButton
    private lateinit var mapButton: ImageButton
    private lateinit var chatButton: ImageButton
    private lateinit var profileButton: ImageButton

    private lateinit var toolbar: Toolbar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_item)

        toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)
        InitButtons()
        InitTalisman()
        InitMagic()
        InitArmor()

        ButtonsLogic()

    }

    private  fun InitTalisman()
    {
        val talismans = listOf(
            Talisman(R.string.boltdrakeTalisman,R.string.boltdrakeDescription, R.string.boltdrakeEffect, R.drawable.boltdrake),
            Talisman(R.string.erdtreeFavorTalisman,R.string.erdtreeFavorDescription, R.string.erdtreeFavorEffect, R.drawable.erdtreefavor),
            Talisman(R.string.roarMedallionTalisman,R.string.roarMedallionDescription, R.string.roarMedallionEffect, R.drawable.roarmedallion),
            Talisman(R.string.flamedrakeTalisman,R.string.flamedrakeDescription, R.string.flamedrakeEffect, R.drawable.flamedrake),
            Talisman(R.string.arrowTalisman,R.string.arrowDescription, R.string.arrowEffect, R.drawable.arrow),
            Talisman(R.string.branchswordTalisman,R.string.branchswordDescription, R.string.branchswordEffect, R.drawable.branchsword),
            Talisman(R.string.assassinTalisman,R.string.assassinDescription, R.string.assassinEffect, R.drawable.assasin),
            Talisman(R.string.hammerTalisman,R.string.hammerDescription, R.string.hammerEffect, R.drawable.hammer),
            Talisman(R.string.axeTalisman,R.string.axeDescription, R.string.axeEffect, R.drawable.axe),
            Talisman(R.string.turtleTalisman,R.string.turtleDescription, R.string.turtleEffect, R.drawable.turtle),
            Talisman(R.string.dancerTalisman,R.string.dancerDescription, R.string.dancerEffect, R.drawable.dancer),
            Talisman(R.string.curvedTalisman,R.string.curvedDescription, R.string.curvedEffect, R.drawable.curved),
        )

        val talismanImageIds = listOf(
            R.id.item1Image,
            R.id.item2Image,
            R.id.item3Image,
            R.id.item4Image,
            R.id.item5Image,
            R.id.item6Image,
            R.id.item7Image,
            R.id.item8Image,
            R.id.item9Image,
            R.id.item10Image,
            R.id.item11Image,
            R.id.item12Image,
        )
        for (i in talismans.indices) {
            SetTalismanImage(talismanImageIds[i], talismans[i].image)
        }
    }

    private  fun InitMagic()
    {
        val magics = listOf(
            Magic(R.string.dragonfirename, R.string.dragonfireDescription, R.string.dragonfireType, Attributes(0,15,12), R.drawable.dragonfire),
            Magic(R.string.tailname, R.string.tailDescription, R.string.tailType, Attributes(0,27,0), R.drawable.tail),
            Magic(R.string.mawname, R.string.mawDescription, R.string.mawType, Attributes(0,24,16), R.drawable.maw),
            Magic(R.string.clawname, R.string.clawDescription, R.string.clawType, Attributes(0,17,13), R.drawable.claw),
        )

        val magicsImages = listOf(
            R.id.item13Image,
            R.id.item14Image,
            R.id.item16Image,
            R.id.item17Image,
        )
        for (i in magics.indices) {
            SetTalismanImage(magicsImages[i], magics[i].image)
        }
    }

    private  fun InitArmor()
    {
        val armors = listOf(
            Armor(R.string.scarabname, R.string.scarabDescription, DamageNegation(-5.8,-5.6,-5.8,-5.8,-4.9,-4.9,-4.9,-5.1), R.drawable.scarab),
            Armor(R.string.pumpkinname, R.string.pumpkinDescription, DamageNegation(7.0,5.9,6.7,6.7,4.6,4.7,5.2,4.5), R.drawable.pumpkin),
            Armor(R.string.scaledname, R.string.scaledDescription, DamageNegation(5.8,5.0,6.1,5.8,4.8,5.0,4.6,4.8), R.drawable.scaled),
            Armor(R.string.commondname, R.string.commonDescription, DamageNegation(0.9,1.8,1.8,1.4,4.4,4.0,4.2,4.4), R.drawable.common),
            Armor(R.string.bluedname, R.string.blueDescription, DamageNegation(2.8,2.8,2.3,2.3,3.1,3.4,3.8,2.8), R.drawable.blue),
            Armor(R.string.twinname, R.string.twinDescription, DamageNegation(4.8,4.4,5.2,4.2,4.0,4.0,3.1,3.6), R.drawable.twin),
            Armor(R.string.catname, R.string.catDescription, DamageNegation(5.8,5.2,5.8,6.1,5.0,5.8,4.8,5.0), R.drawable.cat),
            Armor(R.string.sagename, R.string.sageDescription, DamageNegation(2.3,2.1,2.1,1.4,4.8,4.5,4.6,4.8), R.drawable.sage),
        )

        val armorsImage = listOf(
            R.id.item18Image,
            R.id.item19Image,
            R.id.item20Image,
            R.id.item21Image,
            R.id.item22Image,
            R.id.item23Image,
            R.id.item24Image,
            R.id.item25Image,
        )
        for (i in armors.indices) {
            SetTalismanImage(armorsImage[i], armors[i].image)
        }
    }

    private fun SetTalismanImage(index:Int, image:Int)
    {
        val imageTalisman = findViewById<ImageView>(index)
        imageTalisman.setImageResource(image)
    }



    private fun InitButtons() {
        classesButton = findViewById(R.id.classes)
        bossButton = findViewById(R.id.boss)
        mapButton = findViewById(R.id.map)
        chatButton = findViewById(R.id.chat)
        profileButton = findViewById(R.id.profile)
    }

    private fun ButtonsLogic() {
        classesButton.setOnClickListener { ChangeActivity(ClassesActivity::class.java) }
        bossButton.setOnClickListener { ChangeActivity(BossActivity::class.java) }
        mapButton.setOnClickListener { ChangeActivity(MapActivity::class.java) }
        chatButton.setOnClickListener { ChangeActivity(ClassesActivity::class.java) }
        profileButton.setOnClickListener { ChangeActivity(ClassesActivity::class.java) }
    }

    private fun ChangeActivity(activityClass: Class<*>) {
        val intent = Intent(this, activityClass)
        startActivity(intent)
    }
}