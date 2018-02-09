<template>
  <div class="order-confirm">
      <div class="order-confirm-address">
          <div class="address-view">
              <div class="view-info">
                  <p class="info-receiver">邓海标 136****5830</p>
                  <p class="info-address">广东韶关市仁化县城区新城路2号新政府大楼110室</p>
              </div>
              <div class="view-icon">
                  <i class="iconfont">&#xe639;</i>
              </div>
          </div>
      </div>
  </div>
</template>

<style lang="less" scoped>
.order-confirm{
    .order-confirm-address{
        padding-bottom:5px;
        background: white url(../../assets/images/order_confirm_bg.png) -7px bottom repeat-x;
        background-size: 64px 5px;
        .address-view{
            display: flex;
            padding:10px;
            .view-icon{
                display: flex;
                flex-basis:35px;
                justify-content: flex-end;
                align-items: center;
                i{
                    font-size: 20px;
                }
            }
            .view-info{
                display: flex;
                flex-direction: column;
                flex:1;
                .info-receiver{
                    flex-basis: 25px;
                    line-height: 25px;
                    font-size: 16px;
                    font-weight: 600;
                }
                .info-address{
                    flex:1;
                    font-size: 14px;
                }
            }
        }
    }
}
</style>

