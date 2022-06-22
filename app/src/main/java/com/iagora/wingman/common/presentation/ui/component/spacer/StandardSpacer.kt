package com.iagora.wingman.common.presentation.ui.component.spacer

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Composable
fun StandardSpacer2Dp() = Spacer(modifier = Modifier.size(2.dp))

@Composable
fun StandardSpacer4Dp() = Spacer(modifier = Modifier.size(4.dp))

@Composable
fun StandardSpacer8Dp() = Spacer(modifier = Modifier.size(8.dp))

@Composable
fun StandardSpacer16Dp() = Spacer(modifier = Modifier.size(16.dp))

@Composable
fun StandardSpacer18Dp() = Spacer(modifier = Modifier.size(18.dp))

@Composable
fun StandardSpacer20Dp() = Spacer(modifier = Modifier.size(20.dp))

@Composable
fun StandardSpacer24Dp() = Spacer(modifier = Modifier.size(24.dp))

@Composable
fun StandardSpacer32Dp() = Spacer(modifier = Modifier.size(32.dp))

@Composable
fun StandardSpacer64Dp() = Spacer(modifier = Modifier.size(64.dp))

@Composable
fun StandardSpacer128Dp() = Spacer(modifier = Modifier.size(128.dp))

@Composable
fun StandardSpacer256Dp() = Spacer(modifier = Modifier.size(256.dp))

@Composable
fun StandardSpacerCustomDp(customSize: Dp = 0.dp) = Spacer(modifier = Modifier.size(customSize))