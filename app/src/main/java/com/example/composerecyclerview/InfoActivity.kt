package com.example.composerecyclerview

import android.os.Bundle
import android.widget.Toast
import TvShowListItem
import android.content.Context
import android.content.Intent
import androidx.activity.ComponentActivity
import androidx.compose.runtime.remember
import androidx.activity.compose.setContent
import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.clickable
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import com.example.composerecyclerview.data.TvShowList
import com.example.composerecyclerview.model.TvShow
import com.example.composerecyclerview.ui.theme.ComposeRecyclerViewTheme

class InfoActivity : ComponentActivity() {
    companion object{
        private const val TvShowId = "tvshowid"
        fun intent(context: Context, tvShow: TvShow)=
            Intent(context, InfoActivity::class.java).apply {
                putExtra(TvShowId, tvShow)
            }
    }

    private val tvShow: TvShow by lazy {
        intent?.getSerializableExtra(TvShowId) as TvShow
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ViewMoreInfo(tvShow = tvShow)
        }
    }
}

@Composable
fun ViewMoreInfo(tvShow: TvShow) {
    val scrollState : ScrollState = rememberScrollState()
    Card(
        modifier = Modifier
            .padding(10.dp),
        elevation = 10.dp,
        shape = RoundedCornerShape(corner = CornerSize(10.dp))
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(scrollState)
                .padding(10.dp)
        ) {
            Image(
                painter = painterResource(id = tvShow.imageId),
                contentDescription = null,
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(shape = RoundedCornerShape(4.dp)),
                contentScale = ContentScale.Fit
            )
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                text = tvShow.name,
                style = MaterialTheme.typography.h3
            )
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                text = tvShow.overview,
                style = MaterialTheme.typography.h5
            )
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                text = "Original release : ${tvShow.year}",
                style = MaterialTheme.typography.h5
            )
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                text = "IMDB : ${tvShow.rating}",
                style = MaterialTheme.typography.h3
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview2() {
    ComposeRecyclerViewTheme {

    }
}