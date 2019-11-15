package sk.bumaza.semicirclegraph

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import sk.bumaza.semicirclelibrary.AnglesAnimation
import sk.bumaza.semicirclelibrary.SemiCircleGraphView

class MainActivity : AppCompatActivity() {

    lateinit var graph: SemiCircleGraphView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        graph = findViewById(R.id.demoGraph)

        graph.setPositiveAndNegative(0F, 0F)

        val arcAnimation = AnglesAnimation(graph, 120F, -40F, true)
        arcAnimation.fillAfter = true
        arcAnimation.duration = 1000
        graph.startAnimation(arcAnimation)
    }
}
