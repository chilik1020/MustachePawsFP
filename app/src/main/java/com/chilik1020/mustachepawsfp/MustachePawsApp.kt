package com.chilik1020.mustachepawsfp

import android.app.Application
import com.chilik1020.mustachepawsfp.di.AppModule
import com.chilik1020.mustachepawsfp.di.InteractorModule
import com.chilik1020.mustachepawsfp.di.NetworkModule
import com.chilik1020.mustachepawsfp.di.RepositoryModule
import toothpick.Scope
import toothpick.ktp.KTP

class MustachePawsApp : Application() {
    lateinit var scope: Scope
    override fun onCreate() {
        super.onCreate()
        initToothPick()
    }

    private fun initToothPick() {
        scope = KTP.openScope(Application::class.java)
            .installModules(
                AppModule(this),
                NetworkModule(),
                RepositoryModule(),
                InteractorModule()
            )
    }

    override fun onTerminate() {
        super.onTerminate()
        scope.release()
    }
}