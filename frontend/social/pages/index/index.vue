<template>
	<view class="content">
		<image class="logo" src="https://gimg2.baidu.com/image_search/src=http%3A%2F%2Fimg-cdn-qiniu.dcloud.net.cn%2Fuploads%2Farticle%2F20191014%2F56f7dc1bd5f265e824649f7cb4f78d5b.png&refer=http%3A%2F%2Fimg-cdn-qiniu.dcloud.net.cn&app=2002&size=f9999,10000&q=a80&n=0&g=0n&fmt=jpeg?sec=1641807302&t=50319a31e69dbf9e4322a51bbcae44fa"></image>
		<view class="text-area">
			<text class="title">{{title}}</text>
		</view>
	</view>
</template>

<script>
	export default {
		data() {
			return {
				title: 'Hello'
			}
		},
		onLoad() {

		},
		methods: {

		}
	}
</script>

<style>
	.content {
		display: flex;
		flex-direction: column;
		align-items: center;
		justify-content: center;
	}

	.logo {
		height: 200rpx;
		width: 200rpx;
		margin-top: 200rpx;
		margin-left: auto;
		margin-right: auto;
		margin-bottom: 50rpx;
	}

	.text-area {
		display: flex;
		justify-content: center;
	}

	.title {
		font-size: 36rpx;
		color: #8f8f94;
	}
</style>
