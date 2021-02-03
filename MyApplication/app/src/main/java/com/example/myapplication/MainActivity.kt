package com.example.myapplication

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProviders
import butterknife.BindView
import butterknife.ButterKnife
import butterknife.OnClick
import com.example.myapplication.commonKotlin.*
import com.example.myapplication.commonKotlin.demoDataBinding.mvp.DataBindingViewModel
import com.example.myapplication.commonKotlin.DemoSendData.usingParcelable.PhieuChi
import com.example.myapplication.commonKotlin.DemoSendData.usingSerializable.ObjectIntent
import com.example.myapplication.commonKotlin.Model.Student
import com.example.myapplication.commonKotlin.Utils.API_LINK
import com.example.myapplication.commonKotlin.Utils.CommonUtils
import com.example.myapplication.commonKotlin.Utils.EXTRA_MESSAGE
import com.example.myapplication.commonKotlin.collection.CollectionDemo
import com.example.myapplication.commonKotlin.delegationUtil.TrimDelegation
import com.example.myapplication.commonKotlin.demoActionBar.ActionBarActivity
import com.example.myapplication.commonKotlin.demoFragment.demoFragmentActivity
import com.example.myapplication.commonKotlin.mvp.presenter.MainActivityPresenterImpl
import com.example.myapplication.commonKotlin.sharePreferences.SharePreferenceActivity
import com.example.myapplication.databinding.ActivityMainBinding
import com.example.myapplication.javaClass.Customer
import java.lang.Integer.parseInt
import java.util.*


data class User(val name: String = "", val age: Int = 0)
class MainActivity : AppCompatActivity() {

    private lateinit var text: TextView
    private lateinit var message: TextView
    private lateinit var btnLoad: Button
    private lateinit var loginBtn: Button
    private lateinit var gotoInitNewActivityWithParams: Button

    @BindView(R.id.b2_button)
    lateinit var b2_button: Button

    @BindView(R.id.b3_button)
    lateinit var b3_button: Button


    private var safeVariable: String = "nam"
    private var delegate = TrimDelegation()
    private var param: String
        get() = delegate.getValue(this, ::param)
        set(value) {
            delegate.setValue(this, ::param, value)
        }

    private lateinit var presenter: MainActivityPresenterImpl
    private lateinit var mainBinding : ActivityMainBinding
    private val viewModel by lazy { ViewModelProviders.of(this).get(DataBindingViewModel::class.java) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_main)

        /*
        *  DataBinding
        *  https://codelabs.developers.google.com/codelabs/android-databinding#0
        * */
        mainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        mainBinding.lifecycleOwner = this
        mainBinding.viewModel = viewModel

        ButterKnife.bind(this)
        text = findViewById(R.id.text)
        message = findViewById(R.id.message)
        println("onCreate")
        demoKotlin()
        presenter = MainActivityPresenterImpl(this)
        presenter.init()
        btnLoad = findViewById(R.id.load_data)
        btnLoad.setOnClickListener {
            presenter.login("AAA", "2") { res ->
                println(res)
            }
        }
        loginBtn = findViewById(R.id.openLoginAct)
        loginBtn.setOnClickListener {
            Log.d("Shrek", "Login action")
            startActivity<LoginActivity>(this)
        }

        gotoInitNewActivityWithParams = findViewById(R.id.gotoInitVC)
        gotoInitNewActivityWithParams.setOnClickListener {
            DemoInitMainActivity.StartActivityWithParams( roomColor = "Mikel Arteta", numOfRooms = Integer.parseInt("10" ?: "0")).startActivity(this)
        }

        b2_button.text = "aaaa"
    }

    @OnClick(R.id.b1_button)
    fun onLoginAction() {
        startActivity<Mvvm2Activity>(this)
    }

    @OnClick(R.id.b2_button)
    fun demoSqlite() {

    }

    @OnClick(R.id.b3_button)
    fun onOpenDataBindingMVVM(){
        startActivity<DataBindingMVVMActivity>(this)
    }

    @OnClick(R.id.demoFragment)
    fun onOpenDemoFragment() {
        startActivity<demoFragmentActivity>(this)
    }

    @OnClick(R.id.demoActionBar)
    fun onOpenActionBar() {
        startActivity<ActionBarActivity>(this)
    }

    @OnClick(R.id.sharePreferences)
    fun onSharePreferences() {
        startActivity<SharePreferenceActivity>(this)
    }

    //MARK: Start activity with animation
    private inline fun <reified T> startActivity(context: Context) {
        startActivity(Intent(context, T::class.java).apply {
            putExtra(EXTRA_MESSAGE(), "1")
            //Pass value using Serializable
            var model = ObjectIntent()
            model.gender = "gay"
            putExtra("extra_object", model)

            // Using parcelable
            val p = PhieuChi("abc", "thu ho")
            val phieuChi: PhieuChi = PhieuChi("Arsenal")
            phieuChi.ngayGio = "Thomase Partey"

            val bundle = Bundle()
            bundle.putParcelable("PHIEU_CHI", p)
            putExtra("MY_PARAMS", bundle)
        })
        overridePendingTransition(R.anim.push_left_in, R.anim.push_left_out);
    }

    private fun demoKotlin() {
        //check null variable
        safeVariable?.let {
            var length = safeVariable.length
            println("length = $length")
        }

        param = "    aabbasd    "
//        var param1 = "   aasdasdasd   "

        setMessage()
//        var total: Int = getTotal(1, 2)
        printStr("hello world")
        printProduct("1", "b")
//        var result: Int? = getStringLength(1)
        usingArray()
//        val fstr1 = "123.45f"
//        var long = fstr1.toIntOrNull()
        //        var floatT : Float? = "A123.5f".toFloat() // throw NumberFormatException
        println("debug")

        //Demo class kotlin
        val person = Person("tuan")
        println("class kotlin: " + person.name)
        person.v()

        //Demo class java
        var custumer = Customer("Hello Thomas Partey")
        println(custumer.name)
        println(Resource.name + " - " + Apple.iPad)
        println(custumer.placeOrder())

        // Creates an Array<String> with values ["0", "1", "4", "9", "16"]
        val asc = Array(5) { i ->
            (i * i).toString()
        }
        asc.forEach { println("itemArray" + it) }

        usingWhenExpression(1)

        var child2 = Child2()
        println("child2: " + child2.prop)
        println(child2.foo())
        println(child2.greaterthanTen(13))
        println(child2.lessThanTen(13))
        //using extention
        println("we".add(" are", " the champions"))

        //demo data class
        val jack = User(name = "Jack", age = 1)
        val olderJack = jack.copy(age = 2)
        println(olderJack)

        val jane = User("Jane", 35)
        println(jane.name + " - " + jane.age)

        val (name, age) = jane
        println("$name, $age years of age")

        //data class model
        val student = Student("1", "hello")
        println(student)

        val student2 = Student(name = "Partey", id = "0")
        println(student2)

        //Demo Class
        //1. Primary Contructor
        val invoice = Invoice(1, "Ozil")
        println("Primary Contructor: " + invoice.numberId)
        //        println(invoice.id)  //throw error :  Id is not property variables
        println(invoice.firstName)

        //2. Secondary constructor
        val customer = CustomerExtendClass("ozil", 1)
        println("Secondary Constructor: " + customer.name)

        var invoice2 = Invoice(1, "auba", 30)
        println(invoice2.firstName)

        //3. Interface
        var depart = Derparment(1)
        println(depart.getLocation())

        //abstract class
        var customerExtendClass: CustomerExtendClass = CustomerExtendClass("abc", 2)
        println(customerExtendClass.calculate())
        println(customerExtendClass.addtraction())

        //common , util function
        CommonUtils.helloWord("abc")

        //Singleton
        val singleton: SingletonKT = SingletonKT.instance
        println(singleton.getLegs())
        singleton.setLegs(2)
        println(singleton.getLegs())

        println(API_LINK())

        //call back function. Completion handler
        customerExtendClass.callBackFunction { res -> println(res) }
        customerExtendClass.callBackFunction2 { res, arr ->
            println("callback function" + arr[0])
        }

        doSomethingWithNumber(1000, ::processWithResult)
        doSomethingWithNumber(2000, { result -> })

        println("lastest")
        collectionAndGenericDemo(Direction.EAST)
    }

    private fun collectionAndGenericDemo(enum: Direction) {
        //demo enum
        when (enum) {
            Direction.EAST -> print("EAST")
            Direction.NORTH -> print("NORTH")
            Direction.WEST -> print("WEST")
            else -> {
                print("SOUTH")
            }
        }


        //lamda
        var arrLamda = arrayOf("0", "2", "4", "6", "9")
        arrLamda.map { res -> println(res) }

        //collection and generic
        var generic: CollectionDemo<Int> = CollectionDemo<Int>(1)
        println("collection and generic " + generic.value)

        //immutable collection
//        var imutableArr: List<Int> = listOf(1, 2, 3, 4, 5) // [1,2,3,4,5]
//        var list1: List<Int> = List(5, { index ->
//            index + 1
//        })  // [2,3,4,5,6]

//        list.add(5) //compiler sẽ báo lỗi
//        list.clear() // compiler sẽ báo lỗi

        //mutable collection
        var list: MutableList<Int> = mutableListOf(1, 2, 3, 4, 5) // [1,2,3,4,5]
        var list1: MutableList<Int> = MutableList(5) { index ->
            index + 1
        }  // [2,3,4,5,6]

        list.max()
        list1.count { it % 2 == 0 }
        var res = list.sumBy {
            it % 3
        } // get sum by codition
        list1.drop(1)

        list.add(2) // OK
        list.clear() // OK

        var arr = mutableListOf(1, 2, 4, 1, 7)
        try {
            var results = arr.first { it > 9 }
            print(results) // 4
        } catch (ex: NoSuchElementException) {
            println(ex.message)
        } catch (e: Exception) {
            println(e.message)
        }

        // partition array
        var arrPartition = listOf(1, 2, 4, 2, 7)
        var resultArrPartition = arrPartition.partition { it % 2 == 0 }
        print(resultArrPartition)

        var listA = listOf(1, 2, 4)
        var listB = listOf(3, 4, 6, 7)
        var resultZip = listA.zip(listB)
        println(resultZip) // [(1, 3), (2, 4), (4, 6), (2, 7)]
    }

    //    Higher order funtion
    fun doSomethingWithNumber(number: Int, receiver: (String?) -> Unit) {
        var result: String? = null
        result = "Hello"
        println(result)
        println("doSomethingWithNumber")
        //...do complex work with number

        receiver(result)
        println("finished doSomethingWithNumber")
    }

    //    Function References
    fun processWithResult(result: String?): Unit {
        println("Function References")
        println(result)
        println("Finished Function References")

        // do something with result
    }

    private fun usingWhenExpression(x: Int) {
        when (x) {
            1 -> println("x == 1")
            2 -> println("x == 2")
            3 -> {
                println("x == 3")
            }
            else -> { // Note the block
                println("x is neither 1 nor 2")
            }
        }
    }

    private fun usingArray() {
        val items = listOf("apple", "banana", "kiwifruit")

        //for
        for (index in items.indices) {
            println("item at $index is ${items[index]}")
        }

        // for in
        for (x in 1..5) {
            print(x)
        }

        // for with step
        for (x in 1..10 step 2) {
            print(x)
        }
        println()
        for (x in 9 downTo 0 step 3) {
            print(x)
        }

        //lamda function
        println()
        val fruits = listOf("banana", "avocado", "apple", "kiwifruit")
        fruits
            .filter { it.startsWith("a") }
            .sortedBy { it }
            .map { it.toUpperCase() }
            .forEach { println("lamda $it") }

    }

    private fun setMessage() {
        message.text = "Arsenal.com"
    }

    private fun getTotal(a: Int, b: Int): Int {
        println("sum of $a and $b is ${a + b}")
        return a + b
    }

    private fun printStr(a: String): String {
        return a
    }

    //check type cast and use any
    private fun getStringLength(obj: Any): Int? {
        if (obj is String) {
            // `obj` is automatically cast to `String` in this branch
            return obj.length
        }

        // `obj` is still of type `Any` outside of the type-checked branch
        return null
    }

    private fun printProduct(arg1: String, arg2: String) {
        try {
            val x = parseInt(arg1)
            val y = parseInt(arg2)
            // Using `x * y` yields error because they may hold nulls.
            if (x != null && y != null) {
                // x and y are automatically cast to non-nullable after null check
                println(x * y)
            } else {
                println("'$arg1' or '$arg2' is not a number")
            }
        } catch (e: Exception) {
            // handler
            println(e.localizedMessage)
        } finally {
            // optional finally block
            println("final")
        }
    }

    override fun onStart() {
        super.onStart()
//        Log.d("onStart", "success")
        println("onStart")
    }

    override fun onResume() {
        super.onResume()
//        Log.d("onResume", "success")
        println("onResume")
    }

    override fun onPause() {
        super.onPause()
//        Log.d("onPause", "success")
        println("onPause")
    }

    override fun onStop() {
        super.onStop()
//        Log.d("onStop", "success")
        println("onStop")
    }

    override fun onDestroy() {
        super.onDestroy()
//        Log.d("onDestroy", "success")
        println("onDestroy")
    }

    override fun onRestart() {
        super.onRestart()
//        Log.d("onRestart", "success")
        println("onRestart")
    }
}

// extention
fun Child2.lessThanTen(x: Int): Boolean {
    return x < 10
}

//extention String
fun String.add(str1: String, str2: String): String {
    return this + str1 + str2
}
