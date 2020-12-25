package com.chilik1020.mustachepawsfp.di.viewModels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.chilik1020.mustachepawsfp.ui.login.LoginViewModel
import com.chilik1020.mustachepawsfp.ui.postcreate.PostCreateViewModel
import com.chilik1020.mustachepawsfp.ui.postlist.PostListViewModel
import com.chilik1020.mustachepawsfp.ui.profile.ProfileViewModel
import com.chilik1020.mustachepawsfp.ui.signup.SignUpViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
import javax.inject.Singleton

@Module
abstract class ViewModelModule {
    @Binds
    abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory

    @Binds
    @IntoMap
    @ViewModelKey(LoginViewModel::class)
    abstract fun bindLoginViewModel(viewModel: LoginViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(SignUpViewModel::class)
    abstract fun bindSignUpViewModel(viewModel: SignUpViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(PostListViewModel::class)
    abstract fun bindPostListViewModel(viewModel: PostListViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(ProfileViewModel::class)
    abstract fun bindProfileViewModel(viewModel: ProfileViewModel): ViewModel

    @Singleton
    @Binds
    @IntoMap
    @ViewModelKey(PostCreateViewModel::class)
    abstract fun bindPostCreateViewModel(viewModel: PostCreateViewModel): ViewModel
}