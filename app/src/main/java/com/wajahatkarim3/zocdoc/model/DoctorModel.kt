package com.wajahatkarim3.zocdoc.model

data class DoctorModel(
    val doctorId: Int,
    val name: String,
    val category: String,
    var rating: Float,
    val address: String,
    val imageUrl: String,
    val timeSlots: ArrayList<String>
) {
    companion object {
        fun random(pos: Int = 0): DoctorModel {
            var names = arrayOf(
                "Dr. Cierra Vega, MD",
                "Dr. Alden Cantrell, MD",
                "Dr. Kierra Gentry, MD, PhD",
                "Dr. Pierre Cox, PhD",
                "Dr. Thomas Crane, BBS",
                "Dr. Alvaro Mcgee, MD"
            )
            var addresses = arrayOf(
                "4050 Barranca Pkwy\nIrvine, CA 92604",
                "1828 El Camino Real\nBurlingame, CA 94010",
                "255 Greenwich St\nNew York, NY 10007",
                "4050 Barranca Pkwy\nIrvine, CA 92604",
                "1828 El Camino Real\nBurlingame, CA 94010",
                "255 Greenwich St\nNew York, NY 10007"
            )
            var images = arrayOf(
                "https://images.pexels.com/photos/762020/pexels-photo-762020.jpeg?auto=compress&cs=tinysrgb&dpr=2&w=500",
                "https://images.pexels.com/photos/3777943/pexels-photo-3777943.jpeg?auto=compress&cs=tinysrgb&dpr=2&w=500",
                "https://images.pexels.com/photos/1181519/pexels-photo-1181519.jpeg?auto=compress&cs=tinysrgb&dpr=2&w=500",
                "https://images.pexels.com/photos/3789888/pexels-photo-3789888.jpeg?auto=compress&cs=tinysrgb&dpr=2&w=500",
                "https://images.pexels.com/photos/5083017/pexels-photo-5083017.jpeg?auto=compress&cs=tinysrgb&dpr=2&w=500",
                "https://images.pexels.com/photos/3777570/pexels-photo-3777570.jpeg?auto=compress&cs=tinysrgb&dpr=2&h=750&w=1260"
            )

            var doctorModel = DoctorModel(
                doctorId = 1,
                name = names[pos],
                category = "Primary Care Doctor",
                rating = (4..10).random() / 2f,
                address = addresses[pos],
                imageUrl = images[pos],
                timeSlots = arrayListOf("11:00 am", "11:30 am", "12:00 pm", "3:00 pm", "3:30 pm")
            )
            return doctorModel
        }
    }
}