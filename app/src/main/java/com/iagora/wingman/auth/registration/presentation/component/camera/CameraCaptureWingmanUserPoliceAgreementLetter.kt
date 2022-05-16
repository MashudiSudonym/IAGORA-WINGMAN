package com.iagora.wingman.auth.registration.presentation.component.camera

import android.net.Uri
import androidx.camera.core.CameraSelector
import androidx.camera.core.ImageCapture
import androidx.camera.core.ImageCapture.CAPTURE_MODE_MAXIMIZE_QUALITY
import androidx.camera.core.Preview
import androidx.camera.core.UseCase
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.sharp.Lens
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.unit.dp
import androidx.core.net.toUri
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.iagora.wingman.common.presentation.ui.component.camera.CameraPreview
import com.iagora.wingman.common.presentation.ui.component.camera.executor
import com.iagora.wingman.common.presentation.ui.component.camera.getCameraProvider
import com.iagora.wingman.common.presentation.ui.component.camera.*
import com.iagora.wingman.common.util.Routing
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.launch
import timber.log.Timber

@ExperimentalPermissionsApi
@ExperimentalCoroutinesApi
@Destination
@Composable
fun CameraCaptureWingmanPoliceAgreementLetter(
    cameraSelector: CameraSelector = CameraSelector.DEFAULT_BACK_CAMERA,
    navigator: DestinationsNavigator,
    routing: Routing = Routing,
) {
    val context = LocalContext.current

    Box(modifier = Modifier
        .navigationBarsPadding()
        .statusBarsPadding()) {
        val lifecycleOwner = LocalLifecycleOwner.current
        val coroutineScope = rememberCoroutineScope()
        var previewUseCase by remember { mutableStateOf<UseCase>(Preview.Builder().build()) }
        val imageCaptureUseCase by remember {
            mutableStateOf(
                ImageCapture.Builder()
                    .setCaptureMode(CAPTURE_MODE_MAXIMIZE_QUALITY)
                    .build()
            )
        }

        Box {
            CameraPreview(
                modifier = Modifier.fillMaxSize(),
                onUseCase = {
                    previewUseCase = it
                }
            )
            Button(
                modifier = Modifier
                    .wrapContentSize()
                    .padding(16.dp)
                    .align(Alignment.BottomCenter),
                colors = ButtonDefaults.buttonColors(backgroundColor = Color.Transparent),
                elevation = ButtonDefaults.elevation(0.dp),
                onClick = {
                    coroutineScope.launch {
                        imageCaptureUseCase.takePicture(context.executor).run {
                            routing.navigateToWingmanDetailDocumentDataFormScreenBackStackToRegistrationWingmanDetailDataScreen(
                                navigator = navigator,
                                imageUserIdCard = Uri.parse("file://dev/null"),
                                imageUserPoliceAgreementLetter = this.toUri(),
                                inclusiveStatus = true,
                            )
                        }
                    }
                }
            ) {
                Icon(
                    imageVector = Icons.Sharp.Lens,
                    contentDescription = "Take picture",
                    tint = Color.White,
                    modifier = Modifier
                        .size(100.dp)
                        .padding(1.dp)
                        .border(1.dp, Color.White, CircleShape)
                )
            }
        }
        LaunchedEffect(previewUseCase) {
            val cameraProvider = context.getCameraProvider()
            try {
                // Must unbind the use-cases before rebinding them.
                cameraProvider.unbindAll()
                cameraProvider.bindToLifecycle(
                    lifecycleOwner, cameraSelector, previewUseCase, imageCaptureUseCase
                )
            } catch (ex: Exception) {
                Timber.e("CameraCapture", "Failed to bind camera use cases", ex)
            }
        }
    }
}