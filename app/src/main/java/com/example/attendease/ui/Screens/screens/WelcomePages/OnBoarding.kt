package com.example.attendease.ui.Screens.screens.WelcomePages


import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.tween
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.attendease.R
import com.example.attendease.ui.CustomBlue
import com.example.attendease.ui.LightBlue
import com.example.attendease.ui.components.BlueTitleTexteCenter
import com.example.attendease.ui.components.NormaleGreyTexte
import kotlinx.coroutines.launch


@Composable
fun OnBoarding(navController: NavController) {
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
            NavigationButtons( pagerState ,navController)

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
                BlueTitleTexteCenter("Pas d'internet ? pas de problème!")
                Spacer(modifier = Modifier.height(40.dp))
                NormaleGreyTexte("AttendEase vous permet de gérer les présences même sans connexion. Idéal pour ceux qui ont du mal à trouver une connexion internet stable.")

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
                BlueTitleTexteCenter("Marquage flexible de la présence")
                Spacer(modifier = Modifier.height(40.dp))
                NormaleGreyTexte("Suivez facilement les présences grâce à une gestion flexible des présences et des absences. Vous avez le contrôle !")
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
                BlueTitleTexteCenter("Présence, gérée pour vous !")
                Spacer(modifier = Modifier.height(40.dp))
                NormaleGreyTexte("Gagnez du temps grâce à la saisie automatisée des présences. Laissez AttendEase s'en charger pour vous !")

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
    activeColor: Color = CustomBlue,
    inactiveColor: Color = LightBlue,
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


@Composable
fun NavigationButtons(pagerState: PagerState, navController: NavController) {
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
                        navController.navigate("Login") // Navigate to login on the last page
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
                text = if (pagerState.currentPage < pagerState.pageCount - 1) "suivant" else "connexion",
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                color = Color.White
            )
        }
    }
}
