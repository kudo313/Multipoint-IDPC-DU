# Multipoint-IDPC-DU
Sử dụng thuật toán đàn kiến (ACO) 2 mức để giải bài toán Tìm mạng đa miền có ràng buộc Multipoint IDPC-DU on nodes:

Input: 
Đồ thị đa hướng có trọng số không âm G = (V, E, W) được đánh màu thuộc tập D

  V là tập các đỉnh, mỗi đỉnh được đánh 1 màu dv thuộc D.
  
  E là tập các cạnh, w(e ) > 0  e  ∈ E.
  
  T là tập các node đích
  
  S là đỉnh nguồn

Output: 

  Đồ thị G’ chứa các đường pi  đi từ S đến các đỉnh đích Ti  ∈ T
  
  G’ thoả mãn ràng buộc DU
  
  Tổng trọng số các cạnh của G’ là nhỏ nhất
  


Ràng buộc DU:

  Có nhiều nhất 1 đường đi từ S đến bất kỳ domain và thăm mỗi domain tối đa 1 lần

![Ảnh1](https://user-images.githubusercontent.com/53222626/119309347-62964c00-bc98-11eb-9d0b-ba8d41ebb0e0.png)
