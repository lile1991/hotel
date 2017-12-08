import fetch from '@/utils/fetch'

let RoomtApi = {
  fullPage: function(path) {
    return "/room/" + path;
  },
  findManage: function(data) {
    return fetch({
      url: this.fullPage('findManage'),
      method: 'post',
      data: data
    });
  }
}

export default RoomtApi;
