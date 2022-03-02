package dev.acuon.imagesliderwithretrofit.ui

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.daimajia.slider.library.Animations.DescriptionAnimation
import com.daimajia.slider.library.SliderLayout
import com.daimajia.slider.library.SliderTypes.BaseSliderView
import com.daimajia.slider.library.SliderTypes.TextSliderView
import com.daimajia.slider.library.Tricks.ViewPagerEx
import dev.acuon.imagesliderwithretrofit.R
import dev.acuon.imagesliderwithretrofit.adapter.MovieAdapter
import dev.acuon.imagesliderwithretrofit.api.ApiService
import dev.acuon.imagesliderwithretrofit.api.MovieItem
import dev.acuon.imagesliderwithretrofit.repository.MovieRepository
import dev.acuon.imagesliderwithretrofit.viewmodel.MovieViewModel
import dev.acuon.imagesliderwithretrofit.viewmodel.MovieViewModelFactory
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity(), BaseSliderView.OnSliderClickListener,
    ViewPagerEx.OnPageChangeListener {

    private val TAG = "MainActivity"
    lateinit var viewModel: MovieViewModel
    private val apiService = ApiService.getInstance()
    private var adapter = MovieAdapter()
//    private lateinit var adapter: MovieAdapter
    private lateinit var list: List<MovieItem>
    private lateinit var sliderImages: HashMap<String, MovieItem>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        sliderImages = HashMap()

        viewModel = ViewModelProvider(this, MovieViewModelFactory(MovieRepository(apiService))).get(ViewModel::class.java) as MovieViewModel
        viewModel.getAllMovies()

        viewModel.movieList.observe(this, Observer {
            Log.d(TAG, "onCreate: $it")
            list = it
            adapter.setMovieList(list)
        })

//        for(movie in list) {
//            sliderImages[movie.name] = movie
//        }
//
//        for (name in sliderImages.keys) {
//            val textSliderView = TextSliderView(this)
//            textSliderView
//                .description(name)
//                .image(sliderImages[name]!!.imageUrl)
//                .setScaleType(BaseSliderView.ScaleType.Fit)
//                .setOnSliderClickListener(this)
//            textSliderView.bundle(Bundle())
//            textSliderView.bundle
//                .putString("extra", name)
//            sliderLayout.addSlider(textSliderView)
//        }
//
//        sliderLayout.setPresetTransformer(SliderLayout.Transformer.Accordion)
//        sliderLayout.setPresetIndicator(SliderLayout.PresetIndicators.Center_Bottom)
//        sliderLayout.setCustomAnimation(DescriptionAnimation())
//        sliderLayout.setDuration(3000)
//        sliderLayout.addOnPageChangeListener(this)

        recyclerview.adapter = adapter
        recyclerview.layoutManager = LinearLayoutManager(this)
        viewModel.errorMessage.observe(this, Observer {
        })

    }

    override fun onSliderClick(slider: BaseSliderView?) {

    }

    override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {
        TODO("Not yet implemented")
    }

    override fun onPageSelected(position: Int) {
        TODO("Not yet implemented")
    }

    override fun onPageScrollStateChanged(state: Int) {
        TODO("Not yet implemented")
    }
}