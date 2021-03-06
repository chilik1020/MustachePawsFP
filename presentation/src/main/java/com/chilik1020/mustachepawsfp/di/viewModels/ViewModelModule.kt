package com.chilik1020.mustachepawsfp.di.viewModels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.chilik1020.mustachepawsfp.ui.login.LoginViewModel
import com.chilik1020.mustachepawsfp.ui.postcreate.PostCreateViewModel
import com.chilik1020.mustachepawsfp.ui.postcreate.SelectLocationViewModel
import com.chilik1020.mustachepawsfp.ui.postlist.PostsViewModel
import com.chilik1020.mustachepawsfp.ui.profile.ProfileViewModel
import com.chilik1020.mustachepawsfp.ui.profileedit.EditProfileViewModel
import com.chilik1020.mustachepawsfp.ui.signup.SignUpViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

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
    @ViewModelKey(PostsViewModel::class)
    abstract fun bindPostsViewModel(viewModel: PostsViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(ProfileViewModel::class)
    abstract fun bindProfileViewModel(viewModel: ProfileViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(EditProfileViewModel::class)
    abstract fun bindEditProfileViewModel(viewModel: EditProfileViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(PostCreateViewModel::class)
    abstract fun bindPostCreateViewModel(viewModel: PostCreateViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(SelectLocationViewModel::class)
    abstract fun provideSelectLocationViewModel(viewModel: SelectLocationViewModel): ViewModel
}