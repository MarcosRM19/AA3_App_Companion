package com.example.aa3_app_companion

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton
import android.widget.ImageView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import models.Armor
import models.AttackPower
import models.Attributes
import models.DamageNegation
import models.Magic
import models.Talisman
import models.Weapon

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
        InitWeapon()

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

        val talismanButtonIds = listOf(
            R.id.buttonitem1,
            R.id.buttonitem2,
            R.id.buttonitem3,
            R.id.buttonitem4,
            R.id.buttonitem5,
            R.id.buttonitem6,
            R.id.buttonitem7,
            R.id.buttonitem8,
            R.id.buttonitem9,
            R.id.buttonitem10,
            R.id.buttonitem11,
            R.id.buttonitem12,
        )

        for (i in talismans.indices) {
            SetTalismanImage(talismanImageIds[i], talismans[i].image)
            val button = findViewById<Button>(talismanButtonIds[i])
            button.setOnClickListener {
                OpenTalismanActivity(TalismanActivity::class.java, talismans[i])
            }
        }
    }

    private fun OpenTalismanActivity(activityClass: Class<*>, item: Talisman) {
        val intent = Intent(this, activityClass)
        intent.putExtra("NAME", getString(item.name))
        intent.putExtra("DESCRIPTION", getString(item.description))
        intent.putExtra("EFFECT", getString(item.effect))
        intent.putExtra("IMAGE", item.image)
        startActivity(intent)
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


        val magicButtonIds = listOf(
            R.id.buttonitem13,
            R.id.buttonitem14,
            R.id.buttonitem15,
            R.id.buttonitem16,
        )

        for (i in magics.indices) {
            SetTalismanImage(magicsImages[i], magics[i].image)
            val button = findViewById<Button>(magicButtonIds[i])
            button.setOnClickListener {
                OpenMagicActivity(MagicActivity::class.java, magics[i])
            }
        }
    }

    private fun OpenMagicActivity(activityClass: Class<*>, item: Magic) {
        val intent = Intent(this, activityClass)
        intent.putExtra("NAME", getString(item.name))
        intent.putExtra("DESCRIPTION", getString(item.description))
        intent.putExtra("TYPE", getString(item.type))
        intent.putExtra("INT", item.attribute.int)
        intent.putExtra("FAITH", item.attribute.faith)
        intent.putExtra("ARCANE", item.attribute.arcane)
        intent.putExtra("IMAGE", item.image)
        startActivity(intent)
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

        val armorButtonIds = listOf(
            R.id.buttonitem17,
            R.id.buttonitem18,
            R.id.buttonitem19,
            R.id.buttonitem20,
            R.id.buttonitem21,
            R.id.buttonitem22,
            R.id.buttonitem23,
            R.id.buttonitem24,
        )

        for (i in armors.indices) {
            SetTalismanImage(armorsImage[i], armors[i].image)
            val button = findViewById<Button>(armorButtonIds[i])
            button.setOnClickListener {
                OpenArmorActivity(ArmorActivity::class.java, armors[i])
            }
        }
    }

    private fun OpenArmorActivity(activityClass: Class<*>, item: Armor) {
        val intent = Intent(this, activityClass)
        intent.putExtra("NAME", getString(item.name))
        intent.putExtra("DESCRIPTION", getString(item.description))
        intent.putExtra("STD", item.DamageNegation.Standard.toString())
        intent.putExtra("STR", item.DamageNegation.Strike.toString())
        intent.putExtra("PRC", item.DamageNegation.Pierce.toString())
        intent.putExtra("SLS", item.DamageNegation.Slash.toString())
        intent.putExtra("MGC", item.DamageNegation.Magic.toString())
        intent.putExtra("FRE", item.DamageNegation.Fire.toString())
        intent.putExtra("LGT", item.DamageNegation.Lightning.toString())
        intent.putExtra("HLY", item.DamageNegation.Holy.toString())
        intent.putExtra("IMAGE", item.image)
        startActivity(intent)
    }

    private  fun InitWeapon()
    {
        val weapons = listOf(
            Weapon(R.string.daggername, R.string.daggerDescription, AttackPower(74,0,0,0,0,130),R.drawable.dagger),
            Weapon(R.string.halbertname, R.string.halbertDescription, AttackPower(134,0,0,0,0,100),R.drawable.halberd),
            Weapon(R.string.reduvianame, R.string.reduviaDescription, AttackPower(79,0,0,0,0,110),R.drawable.reduvia),
            Weapon(R.string.steelname, R.string.steelname, AttackPower(67,0,0,0,0,110),R.drawable.steel),
            Weapon(R.string.fangname, R.string.fangDescription, AttackPower(141,0,0,0,0,100),R.drawable.fang),
            Weapon(R.string.estocname, R.string.estocDescription, AttackPower(107,0,0,0,0,100),R.drawable.estoc),
            Weapon(R.string.pickname, R.string.pickDescription, AttackPower(108,0,0,0,0,100),R.drawable.pick),
            Weapon(R.string.greataxename, R.string.greataxeDescription, AttackPower(151,0,0,0,0,100),R.drawable.greataxe),
            Weapon(R.string.uchigatananame, R.string.uchigatanaDescription, AttackPower(115,0,0,0,0,100),R.drawable.uchigatana),
            Weapon(R.string.shirname, R.string.shirDescription, AttackPower(108,0,0,0,0,100),R.drawable.shir),
            Weapon(R.string.spearname, R.string.spearDescription, AttackPower(112,0,0,0,0,100),R.drawable.spear),
            Weapon(R.string.longspearname, R.string.longspearDescription, AttackPower(114,0,0,0,0,100),R.drawable.longspear),
            Weapon(R.string.greatswordname, R.string.greatswordDescription, AttackPower(136,0,0,0,0,110),R.drawable.greatsword),
            Weapon(R.string.falchionname, R.string.falchionDescription, AttackPower(109,0,0,0,0,100),R.drawable.falchion),
            Weapon(R.string.battlehammername, R.string.battlehammerDescription, AttackPower(131,0,0,0,0,100),R.drawable.battlehammer),
            Weapon(R.string.bowname, R.string.bowname, AttackPower(60,0,0,0,0,100),R.drawable.bow),
        )

        val weaponsImage = listOf(
            R.id.item26Image,
            R.id.item27Image,
            R.id.item28Image,
            R.id.item29Image,
            R.id.item30Image,
            R.id.item31Image,
            R.id.item32Image,
            R.id.item33Image,
            R.id.item34Image,
            R.id.item35Image,
            R.id.item36Image,
            R.id.item37Image,
            R.id.item38Image,
            R.id.item39Image,
            R.id.item40Image,
            R.id.item41Image
        )

        val weaponButtonIds = listOf(
            R.id.buttonitem25,
            R.id.buttonitem26,
            R.id.buttonitem27,
            R.id.buttonitem28,
            R.id.buttonitem29,
            R.id.buttonitem30,
            R.id.buttonitem31,
            R.id.buttonitem32,
            R.id.buttonitem33,
            R.id.buttonitem34,
            R.id.buttonitem35,
            R.id.buttonitem36,
            R.id.buttonitem37,
            R.id.buttonitem38,
            R.id.buttonitem39,
            R.id.buttonitem40,
        )

        for (i in weapons.indices) {
            SetTalismanImage(weaponsImage[i], weapons[i].image)
            val button = findViewById<Button>(weaponButtonIds[i])
            button.setOnClickListener {
                OpenWeaponActivity(WeaponActivity::class.java, weapons[i])
            }
        }
    }

    private fun OpenWeaponActivity(activityClass: Class<*>, item: Weapon) {
        val intent = Intent(this, activityClass)
        intent.putExtra("NAME", getString(item.name))
        intent.putExtra("DESCRIPTION", getString(item.description))
        intent.putExtra("PHS", item.attackpower.physical.toString())
        intent.putExtra("MGC", item.attackpower.magic.toString())
        intent.putExtra("FRE", item.attackpower.fire.toString())
        intent.putExtra("LGT", item.attackpower.lightning.toString())
        intent.putExtra("HLY", item.attackpower.holy.toString())
        intent.putExtra("CRT", item.attackpower.critical.toString())
        intent.putExtra("IMAGE", item.image)
        startActivity(intent)
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
        chatButton.setOnClickListener { ChangeActivity(ConversationActivity::class.java) }
        profileButton.setOnClickListener { ChangeActivity(ProfileActivity::class.java) }
    }

    private fun ChangeActivity(activityClass: Class<*>) {
        val intent = Intent(this, activityClass)
        startActivity(intent)
    }
}