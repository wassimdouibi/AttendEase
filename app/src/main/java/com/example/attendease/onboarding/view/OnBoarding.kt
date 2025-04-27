package com.example.attendease.onboarding.view

import android.content.SharedPreferences
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.attendease.R
import com.example.attendease.onboarding.components.BlueTitleTexteCenter
import com.example.attendease.onboarding.components.NormaleGreyTexte
import com.example.attendease.router.Router
import com.example.attendease.ui.theme.LocalCustomColorScheme
import kotlinx.coroutines.launch


@OptIn(ExperimentalFoundationApi::class)
@Composable
fun OnBoarding(
    pref: SharedPreferences,
    navController: NavController
) {
    // State to manage the pager
    val pagerState = rememberPagerState(0,0F,
    ){3}

    Surface(
        color = Color.White,
        modifier = Modifier.fillMaxSize() // to cover the whole screen
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize() // Makes the Column fill the entire screen
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally, // Center horizontally
        ) {

            Spacer(modifier = Modifier.height(210.dp))
            // The pager with 3 pages (slides)
            HorizontalPager(
                state = pagerState,
                modifier = Modifier.fillMaxWidth().height(400.dp)
            ) { page ->
                // Pass the page number to customize the content for each slide
                SlideContent(page)
            }
            Spacer(modifier = Modifier.height(32.dp))
            DotIndicators(
                totalSlides = 3,
                currentPage = pagerState.currentPage
            )

            Spacer(modifier = Modifier.weight(1f))
            NavigationButtons( pagerState ,navController, pref)

            Spacer(modifier = Modifier.height(15.dp))
        }
    }
}

@Composable
fun SlideContent(page: Int) {
    Column(
        modifier = Modifier.fillMaxSize(), // Ensures all slides take the same height
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
    ) {
        // Customize the content for each page
        when (page) {
            0 -> {

                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center,
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.wifi),
                        contentDescription = "wifiIcon",
                        modifier = Modifier.width(140.dp)
                    )

                    Spacer(modifier = Modifier.height(80.dp))
                    BlueTitleTexteCenter(stringResource(R.string.onboarding_title1))
                    Spacer(modifier = Modifier.height(40.dp))
                    NormaleGreyTexte(stringResource(R.string.onboarding_subtitle1))

                }

            }

            1 -> {

                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center,
                ) {
                    Spacer(modifier = Modifier.height(20.dp))
                    Image(
                        painter = painterResource(id = R.drawable.toggle),
                        contentDescription = "toggleIcon",
                        modifier = Modifier.width(180.dp)
                    )

                    Spacer(modifier = Modifier.height(80.dp))
                    BlueTitleTexteCenter(stringResource(R.string.onboarding_title2))
                    Spacer(modifier = Modifier.height(40.dp))
                    NormaleGreyTexte(stringResource(R.string.onboarding_subtitle3))
                }

            }

            2 -> {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center,
                ) {

                    Image(
                        painter = painterResource(id = R.drawable.teacher),
                        contentDescription = "teacherIcon",
                        modifier = Modifier.width(170.dp)
                            .height(200.dp)
                    )

                    Spacer(modifier = Modifier.height(42.dp))
                    BlueTitleTexteCenter(stringResource(R.string.onboarding_title3))
                    Spacer(modifier = Modifier.height(40.dp))
                    NormaleGreyTexte(stringResource(R.string.onboarding_subtitle3))

                    Spacer(modifier = Modifier.height(15.dp))
                }
            }
        }
    }
}

@Composable
fun DotIndicators(
    totalSlides: Int,
    currentPage: Int,
    activeColor: Color = LocalCustomColorScheme.current.primary500,
    inactiveColor: Color = LocalCustomColorScheme.current.primary300,
    activeDotWidth: Dp = 32.dp,  // Wider active dot
    inactiveDotWidth: Dp = 10.dp, // Standard width for inactive dots
    dotHeight: Dp = 10.dp         // Uniform height for all dots
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .padding(bottom = 16.dp),
        horizontalArrangement = Arrangement.Center
    ) {
        for (i in 0 until totalSlides) {
            Box(
                modifier = Modifier
                    .padding(4.dp)
                    .width(if (i == currentPage) activeDotWidth else inactiveDotWidth) // Dynamic width
                    .height(dotHeight)
                    .clip(CircleShape)
                    .background(if (i == currentPage) activeColor else inactiveColor)
            )
        }
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun NavigationButtons(pagerState: PagerState, navController: NavController, pref: SharedPreferences) {
    val coroutineScope = rememberCoroutineScope()

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp),
        horizontalArrangement = Arrangement.SpaceBetween // Space between buttons
    ) {
        // Back Button (Only visible after the first page)
        if (pagerState.currentPage > 0) {
            Button(
                onClick = {
                    coroutineScope.launch {
                        pagerState.animateScrollToPage(
                            page = pagerState.currentPage - 1,
                            animationSpec = tween(durationMillis = 500, easing = FastOutSlowInEasing)
                        )
                    }
                },
                modifier = Modifier
                    .weight(1f)
                    .heightIn(56.dp),
                colors = ButtonDefaults.buttonColors(containerColor = Color.White),
                shape = RoundedCornerShape(100.dp)
            ) {
                Text(
                    text = "Retour",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.Black
                )
            }
            Spacer(modifier = Modifier.width(8.dp)) // Add spacing between buttons
        }

        // Next or Login Button
        Button(
            onClick = {
                coroutineScope.launch {
                    if (pagerState.currentPage < pagerState.pageCount - 1) {
                        pagerState.animateScrollToPage(
                            page = pagerState.currentPage + 1,
                            animationSpec = tween(durationMillis = 500, easing = FastOutSlowInEasing)
                        )
                    } else {
                        pref.edit().putBoolean("IS_ONBOARDING_DONE", true).apply()
                        navController.popBackStack()
                        navController.navigate(Router.LoginScreen.route)
                    }
                }
            },
            modifier = Modifier
                .weight(1f)
                .heightIn(56.dp),
            colors = ButtonDefaults.buttonColors(containerColor = Color.Black),
            shape = RoundedCornerShape(100.dp)
        ) {
            Text(
                text = if (pagerState.currentPage < pagerState.pageCount - 1) stringResource(R.string.onboarding_next_cta) else stringResource(R.string.onboarding_connexion),
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                color = Color.White
            )
        }
    }
}