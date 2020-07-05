<template>
  <view id="loadmore" class="mint-loadmore" @touchstart="handleTouchStart" @touchmove="handleTouchMove" @touchend="handleTouchEnd">
    <view class="mint-loadmore-content" :style="{ 'transform': transform }">
      <view class="mint-loadmore-top">
        <!-- <spinner v-if="topStatus === 'loading'" class="mint-loadmore-spinner" :size="20" type="fading-circle"></spinner>
        <span class="mint-loadmore-text">{{ topText }}</span> -->
        <uniLoadMore :status="topText" :contentText="contentTop"></uniLoadMore>
      </view>
      <slot></slot>
      <view v-if="controlBottom" class="mint-loadmore-bottom" :class="{'loadmore-no': bottomAllLoaded}">
        <!-- <spinner v-if="bottomStatus === 'loading'" class="mint-loadmore-spinner" :size="20" type="fading-circle"></spinner>
        <span class="mint-loadmore-text">{{ bottomText }}</span> -->
        <uniLoadMore v-if="!bottomAllLoaded" :status="bottomText" :contentText="contentBottom"></uniLoadMore>
        <view v-else class="load-more-no">
          <text>{{noMoreText}}</text>
        </view>
      </view>
    </view>
  </view>
</template>

<script>
import uniLoadMore from "@/components/uni-load-more/uni-load-more.vue"
export default {
  components: {
    uniLoadMore
  },
  props: {
    maxDistance: {
      type: Number,
      default: 0
    },
    top: {
      type: Number
    },
    bottom: {
      type: Number
    },
    autoFill: {
      type: Boolean,
      default: true
    },
    distanceIndex: {
      type: Number,
      default: 1
    },
    topMethod: {
      type: Function
    },
    topPullText: {
      type: String,
      default: '下拉刷新'
    },
    topDropText: {
      type: String,
      default: '释放更新'
    },
    topLoadingText: {
      type: String,
      default: '加载中...'
    },
    topDistance: {
      type: Number,
      default: 70
    },
    bottomMethod: {
      type: Function
    },
    bottomPullText: {
      type: String,
      default: '上拉刷新'
    },
    bottomDropText: {
      type: String,
      default: '释放更新'
    },
    bottomLoadingText: {
      type: String,
      default: '加载中...'
    },
    bottomDistance: {
      type: Number,
      default: 70
    },
    bottomAllLoaded: {
      type: Boolean,
      default: false
    },
    noMoreText: {
      type: String,
      default: '没有更多数据了'
    },
    controlBottom: {
      type: Boolean,
      default: true
    }
  },
  data() {
    return {
      translate: 0,
      scrollTop: 0,
      old: {
        scrollTop: 0
      },
      topStatus: '',
      topText: '',
      topDropped: false,
      bottomStatus: '',
      bottomText: '',
      bottomDropped: false,
      bottomReached: false,
      direction: '',
      startY: 0,
      startScrollTop: 0,
      scrollInfo: {},
      contentTop: {
        contentdown: this.topPullText,
        contentrefresh: this.topLoadingText,
        contentnomore: this.topDropText
      },
      contentBottom: {
        contentdown: this.bottomPullText,
        contentrefresh: this.bottomLoadingText,
        contentnomore: this.bottomDropText
      }
    };
  },
  computed: {
    transform() {
      // console.log(this.translate)
      return this.translate === 0 ? null : 'translate3d(0, ' + this.translate + 'px, 0)';
    }
  },
  watch: {
    topStatus(val) {
      switch (val) {
        case 'pull':
          // this.topText = this.topPullText;
          this.topText = 'more'
          break;
        case 'drop':
          // this.topText = this.topDropText;
          this.topText = 'noMore'
          break;
        case 'loading':
          // this.topText = this.topLoadingText;
          this.topText = 'loading'
          break;
      }
    },
    bottomStatus(val) {
      switch (val) {
        case 'pull':
          // this.bottomText = this.bottomPullText;
          this.bottomText = 'more'
          break;
        case 'drop':
          // this.bottomText = this.bottomDropText;
          this.bottomText = 'noMore'
          break;
        case 'loading':
          // this.bottomText = this.bottomLoadingText;
          this.bottomText = 'loading'
          break;
      }
    }
  },
  mounted() {
    this.topStatus = 'pull';
    this.bottomStatus = 'pull';
    // this.topText = this.topPullText;
    this.topText = 'more';
  },
  methods: {
    getScrollTop() {
      let that = this;
      const query = uni.createSelectorQuery().in(this);
      query.select('#loadmore').boundingClientRect(data => {
        // console.log("得到布局位置信息" + JSON.stringify(data));
        that.startScrollTop = data.top
        that.scrollInfo = data
      }).exec();
    },
    handleTouchStart: function(event) {
      try{
        this.startY = event.changedTouches[0].clientY;
      }catch(e){
        //TODO handle the exception
      }
      this.getScrollTop();
      this.bottomReached = false;
      if (this.topStatus !== 'loading') {
        this.topStatus = 'pull';
        this.topDropped = false;
      }
      if (this.bottomStatus !== 'loading') {
        this.bottomStatus = 'pull';
        this.bottomDropped = false;
      }
    },
    handleTouchMove: function(event) {
      // this.getScrollTop();
      if (this.startY < this.top && this.startY > this.bottom) {
        return;
      }
      this.currentY = event.changedTouches[0].clientY;
      let distance = (this.currentY - this.startY) / this.distanceIndex;
      this.direction = distance > 0 ? 'down' : 'up';
      // console.log(this.direction)
      if (typeof this.topMethod === 'function' && this.direction === 'down' && (this.startScrollTop - this.top) == 0 && this.topStatus !== 'loading') {
        event.preventDefault();
        event.stopPropagation();
        if (this.maxDistance > 0) {
          this.translate = distance <= this.maxDistance ? distance : this.translate;
        } else {
          this.translate = distance;
        }
        if (this.translate < 0) {
          this.translate = 0;
        }
        this.topStatus = this.translate >= this.topDistance ? 'drop' : 'pull';
      }

      if (this.direction === 'up') {
        this.bottomReached = this.bottomReached || this.checkBottomReached(distance);
      }

      if (typeof this.bottomMethod === 'function' && this.direction === 'up' &&
        this.bottomReached && this.bottomStatus !== 'loading' && !this.bottomAllLoaded) {
        event.preventDefault();
        event.stopPropagation();
        if (this.maxDistance > 0) {
          this.translate = Math.abs(distance) <= this.maxDistance
            ? (this.scrollInfo.height + distance + this.scrollInfo.top) - this.scrollInfo.bottom : this.translate;
        } else {
          this.translate = (this.scrollInfo.height + distance + this.scrollInfo.top) - this.scrollInfo.bottom;
        }
        if (this.translate > 0) {
          this.translate = 0;
        }
        this.bottomStatus = -this.translate >= this.bottomDistance ? 'drop' : 'pull';
      }
    },
    handleTouchEnd: function(event) {
      if (this.direction === 'down' && (this.startScrollTop - this.top) == 0 && this.translate > 0) {
        this.topDropped = true;
        if (this.topStatus === 'drop') {
          this.translate = '50';
          this.topStatus = 'loading';
          // this.topMethod();
          this.$emit('topMethods')
        } else {
          this.translate = '0';
          this.topStatus = 'pull';
        }
      }
      if (this.direction === 'up' && this.bottomReached && this.translate < 0) {
        this.bottomDropped = true;
        this.bottomReached = false;
        if (this.bottomStatus === 'drop') {
          this.translate = '-50';
          this.bottomStatus = 'loading';
          // this.bottomMethod();
          this.$emit('bottomMethods')
        } else {
          this.translate = '0';
          this.bottomStatus = 'pull';
        }
      }
      this.direction = '';
    },
    checkBottomReached(distance) {
      // console.log(this.scrollInfo.height + distance + this.scrollInfo.top)
      return this.scrollInfo.height + distance + this.scrollInfo.top <= this.scrollInfo.bottom
    },
    onTopLoaded() {
      this.translate = 0;
      setTimeout(() => {
        this.topStatus = 'pull';
      }, 200);
    },
    onBottomLoaded() {
      this.bottomStatus = 'pull';
      this.bottomDropped = false;
      this.$nextTick(() => {
        this.translate = 0;
      });
    }
  }
};
</script>

<style>
.mint-loadmore {
    overflow: hidden
}
.mint-loadmore-content {}
.mint-loadmore-content.is-dropped {
    -webkit-transition: .2s;
    transition: .2s
}
.mint-loadmore-top, .mint-loadmore-bottom {
    text-align: center;
    height: 50px;
    line-height: 50px
}
.mint-loadmore-top {
    margin-top: -50px
}
.mint-loadmore-bottom {
    margin-bottom: -50px
}
.loadmore-no {
  margin-bottom: 0px;
}
.mint-loadmore-spinner {
    display: inline-block;
    margin-right: 5px;
    vertical-align: middle
}
.mint-loadmore-text {
    vertical-align: middle
}
.load-more-no {
  height: 40px;
  display: flex;
  justify-content: center;
  align-items: center;
  font-size: 14px;
}
</style>
