import fetch from '@/utils/fetch'

let RoomTypeApi = {
  fullPage: function(path) {
    return "/roomType/" + path;
  },
  findAll: function() {
    return fetch({
      url: this.fullPage('findAll'),
      method: 'get'
    });
  }
}

export default RoomTypeApi;
