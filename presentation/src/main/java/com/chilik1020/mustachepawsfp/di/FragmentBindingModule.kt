package com.chilik1020.mustachepawsfp.di

import com.chilik1020.mustachepawsfp.ui.login.LoginFragment
import com.chilik1020.mustachepawsfp.ui.postcreate.PostCreateFragment
import com.chilik1020.mustachepawsfp.ui.postcreate.SelectLocationDialogFragment
import com.chilik1020.mustachepawsfp.ui.postlist.PostsFragment
import com.chilik1020.mustachepawsfp.ui.profile.ProfileFragment
import com.chilik1020.mustachepawsfp.ui.signup.SignUpFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector
import dagger.android.support.AndroidSupportInjectionModule

@Module(includes = [AndroidSupportInjectionModule::class])
abstract class FragmentBindingModule {

    @ContributesAndroidInjector
    abstract fun loginFragment(): LoginFragment

    @ContributesAndroidInjector
    abstract fun signUpFragment(): SignUpFragment

    @ContributesAndroidInjector
    abstract fun postsFragment(): PostsFragment

    @ContributesAndroidInjector
    abstract fun profileFragment(): ProfileFragment

//    @ContributesAndroidInjector
//    abstract fun imageCaptureFragment(): ImageCaptureFragment

//    @ContributesAndroidInjector
//    abstract fun typeHelpDialogFragment(): TypeHelpDialogFragment
//
//    @ContributesAndroidInjector
//    abstract fun typeAnimalDialogFragment(): TypeAnimalDialogFragment
//
//    @ContributesAndroidInjector
//    abstract fun postDescriptionDialogFragment(): PostDescriptionDialogFragment
//

    @ContributesAndroidInjector
    abstract fun postCreateFragment(): PostCreateFragment

    @ContributesAndroidInjector
    abstract fun selectLocationFragment(): SelectLocationDialogFragment
}