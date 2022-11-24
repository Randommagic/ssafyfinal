import { apiInstance } from "./index.js";

const api = apiInstance();

function writeComment(comment, success, fail) {
  api.post(`/comment`, JSON.stringify(comment)).then(success).catch(fail);
}

async function listComment(articleno, success, fail) {
  await api.get(`/comment/${articleno}`).then(success).catch(fail);
}
export {writeComment, listComment};