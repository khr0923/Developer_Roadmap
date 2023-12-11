package com.example.mp_pr

import android.app.AlertDialog
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuItem
import android.view.MenuInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import com.google.android.material.appbar.MaterialToolbar
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import androidx.viewpager.widget.ViewPager
import com.example.mp_pr.SignUp.SignUpActivity
import com.example.mp_pr.login.LogInActivity
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.tabs.TabLayout
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class MyPageFragment : Fragment(){
    //private  lateinit var logOutBtn : Button
    //private  lateinit var signOutBtn : Button
    //private lateinit var auth: FirebaseAuth
    private lateinit var toolbar: MaterialToolbar
    val db = Firebase.firestore
    var uid = FirebaseAuth.getInstance().currentUser?.uid

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_my_page, container, false)
        val viewPager: ViewPager = view.findViewById(R.id.viewpager)
        val tabLayout: TabLayout = view.findViewById(R.id.tablayout)
        val toolbar: MaterialToolbar = view.findViewById(R.id.mypage_toolbar)



        val adapter = MyFragmentPagerAdapter(childFragmentManager)
        adapter.addFragment(MyContentFragment(), "내가 쓴 글")
        adapter.addFragment(MyCommentFragment(), "내 댓글")






        viewPager.adapter = adapter
        tabLayout.setupWithViewPager(viewPager)


        toolbar.setOnMenuItemClickListener {menuItem: MenuItem ->
            when(menuItem.itemId) {
                R.id.logOut_menu -> {
                    FirebaseAuth.getInstance().signOut()
                    startActivity(Intent(activity, LogInActivity::class.java))
                    true
                }

                R.id.signOut_menu -> {
                    showDialog()
                    true
                }
                else -> false
            }}

        return view
    }

    private fun showDialog(){
        val builder = AlertDialog.Builder(activity)
        builder
            .setTitle("정말 탈퇴하시겠어요?")
            .setMessage("탈퇴 버튼 선택시, 계정은 삭제되며 복구되지 않습니다.")
            .setIcon(R.drawable.logo_img_2)
            .setPositiveButton("확인") { dialog , which ->
                // 기능구현
                Log.v("다이얼로그", "yes")
                FirebaseAuth.getInstance().currentUser?.delete()
                startActivity(Intent(activity, LogInActivity::class.java))

            }
            .setNegativeButton("취소"){ dialog, which ->
                // 기능구현
                Log.v("다이얼로그", "No")
            }
            .create()
            .show()
    }


}