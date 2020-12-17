package com.chilik1020.mustachepawsfp

import android.app.Application
import com.chilik1020.framework.di.AppModule
import com.chilik1020.framework.di.ApplicationScope
import com.chilik1020.framework.di.DataModule
import com.chilik1020.framework.di.NetworkModule
import toothpick.Scope
import toothpick.ktp.KTP

class MustachePawsApp : Application() {
    lateinit var scope: Scope
    override fun onCreate() {
        super.onCreate()
        initToothPick()
    }

    private fun initToothPick() {
        scope = KTP.openScope(ApplicationScope::class.java)
            .installModules(
                AppModule(this),
                NetworkModule(),
                DataModule()
            )
    }

    override fun onTerminate() {
        super.onTerminate()
        scope.release()
    }
}