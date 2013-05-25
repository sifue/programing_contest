import scala.collection.mutable

object FoodChain {
  def main(args: Array[String]) {

    /**
     * 情報を定義する値オブジェクト
     * @param infoType
     * @param x
     * @param y
     */
    case class Info(infoType:Int, x: Int, y: Int)

    // 与えられた情報を定義
//    val maxNumber = 100
//    val infoList = List(Info(1, 101, 1),Info(2, 1, 2),Info(2, 2, 3),Info(2, 3, 3),Info(1, 1, 3),Info(2, 3, 1),Info(1, 5, 5))
    val maxNumber = 10000
    val infoList = List(Info(1, 1682, 4043),Info(1, 2727, 6836),Info(2, 3855, 8064),Info(2, 9065, 892),Info(1, 5393, 3208),Info(2, 4246, 5230),Info(2, 6882, 7671),Info(1, 8442, 6315),Info(2, 8625, 8540),Info(1, 5481, 8332),Info(2, 1388, 3310),Info(1, 2287, 9686),Info(1, 7465, 3579),Info(2, 4427, 8311),Info(1, 6949, 5334),Info(2, 9431, 3818),Info(1, 7396, 3226),Info(1, 2935, 6675),Info(2, 9410, 3035),Info(2, 9310, 4586),Info(1, 1645, 6854),Info(1, 1549, 1164),Info(2, 7199, 9762),Info(1, 621, 6701),Info(2, 4567, 1541),Info(2, 7185, 993),Info(2, 7475, 4433),Info(2, 4142, 6395),Info(1, 3508, 1609),Info(1, 9086, 6375),Info(2, 3331, 7622),Info(2, 3937, 5486),Info(2, 1582, 3895),Info(1, 955, 8447),Info(1, 2441, 5588),Info(1, 2301, 4364),Info(1, 8933, 8266),Info(2, 1698, 277),Info(2, 9152, 2500),Info(1, 4977, 5686),Info(1, 128, 8492),Info(1, 3612, 4499),Info(2, 3566, 5605),Info(1, 2469, 8042),Info(1, 8958, 6665),Info(1, 3560, 8535),Info(2, 8225, 124),Info(1, 7906, 5656),Info(1, 822, 5509),Info(1, 4486, 1125),Info(1, 6198, 2281),Info(1, 4338, 5230),Info(1, 3056, 9441),Info(1, 3074, 5202),Info(1, 536, 9472),Info(1, 5062, 5623),Info(2, 478, 674),Info(1, 6467, 7522),Info(1, 8942, 7746),Info(2, 982, 2947),Info(1, 8647, 4178),Info(1, 1473, 2023),Info(2, 2223, 3462),Info(1, 8979, 9145),Info(2, 1377, 5432),Info(2, 9682, 863),Info(2, 5023, 4397),Info(2, 3659, 8013),Info(2, 388, 375),Info(1, 8751, 6754),Info(2, 6087, 6604),Info(1, 6471, 5644),Info(2, 7971, 4213),Info(2, 7744, 3583),Info(1, 6452, 6425),Info(1, 6155, 7036),Info(2, 3324, 6005),Info(1, 927, 9704),Info(1, 324, 6948),Info(2, 2150, 6435),Info(2, 4199, 1797),Info(2, 2220, 5939),Info(1, 1578, 9524),Info(1, 7925, 3070),Info(1, 2693, 8977),Info(1, 8376, 3722),Info(2, 9645, 7642),Info(1, 9489, 2186),Info(1, 3155, 1499),Info(2, 5825, 8468),Info(2, 9697, 6979),Info(2, 1365, 3019),Info(1, 4104, 2266),Info(2, 20, 9905),Info(1, 5279, 8221),Info(2, 8177, 6265),Info(1, 2409, 7641),Info(1, 4643, 6773),Info(2, 8651, 5274),Info(1, 9891, 1511),Info(2, 8316, 3739),Info(1, 7247, 9815),Info(1, 8490, 2799),Info(1, 4730, 4665),Info(2, 3699, 8440),Info(2, 779, 10008),Info(2, 7935, 8523),Info(1, 6749, 462),Info(2, 1286, 380),Info(2, 6056, 2813),Info(2, 7011, 7446),Info(1, 1075, 1774),Info(2, 7490, 739),Info(1, 7778, 135),Info(1, 5038, 236),Info(2, 1129, 3540),Info(2, 9218, 3728),Info(2, 4114, 2717),Info(1, 1815, 9113),Info(2, 2603, 8250),Info(1, 1734, 2951),Info(2, 6190, 6096),Info(1, 1041, 5301),Info(1, 5713, 4828),Info(2, 3485, 967),Info(1, 5303, 6298),Info(1, 4535, 4015),Info(1, 40, 5679),Info(1, 846, 6930),Info(2, 6781, 403),Info(2, 7730, 3573),Info(1, 4527, 3696),Info(1, 2221, 6710),Info(1, 4450, 4589),Info(1, 3513, 6602),Info(2, 6982, 8633),Info(2, 652, 6510),Info(1, 5700, 9550),Info(2, 374, 9401),Info(1, 9890, 3842),Info(1, 3016, 215),Info(1, 8370, 3766),Info(2, 2036, 4527),Info(2, 2807, 9120),Info(1, 8450, 9933),Info(2, 6256, 1852),Info(2, 1212, 4707),Info(2, 6479, 6620),Info(1, 9324, 5632),Info(1, 9737, 5459),Info(1, 4247, 8682),Info(1, 3923, 9590),Info(2, 9093, 7247),Info(1, 3992, 443),Info(1, 8005, 5165),Info(2, 9562, 9916),Info(2, 6109, 1005),Info(2, 9279, 10052),Info(1, 3129, 3554),Info(1, 7592, 9464),Info(2, 231, 8711),Info(1, 5265, 1779),Info(1, 1586, 7859),Info(1, 4818, 7718),Info(2, 2728, 1982),Info(2, 8665, 7992),Info(1, 4376, 4615),Info(2, 1400, 2387),Info(1, 6947, 8898),Info(1, 2435, 8297),Info(2, 1923, 3775),Info(2, 8131, 231),Info(2, 7933, 5096),Info(1, 812, 3367),Info(1, 6815, 666),Info(1, 3230, 9181),Info(1, 6187, 980),Info(2, 3816, 5514),Info(2, 1076, 5616),Info(2, 3495, 9235),Info(2, 9187, 2233),Info(2, 4621, 2860),Info(1, 5557, 8438),Info(1, 6188, 8529),Info(1, 3092, 1226),Info(1, 2846, 1637),Info(2, 5011, 8601),Info(2, 2753, 742),Info(2, 8349, 7512),Info(1, 3990, 7087),Info(2, 4384, 10030),Info(1, 9575, 592),Info(1, 1000, 1387),Info(2, 7741, 4901),Info(2, 8273, 739),Info(1, 7851, 4184),Info(1, 9858, 5463),Info(1, 6953, 5410),Info(1, 2863, 9046),Info(2, 6286, 929),Info(1, 8620, 2979),Info(1, 667, 3372),Info(2, 9109, 5417),Info(1, 1625, 6367),Info(2, 3094, 8235),Info(1, 7861, 7452),Info(1, 3359, 5111),Info(1, 4894, 487),Info(2, 9305, 3848),Info(1, 9264, 2891),Info(1, 2733, 9545),Info(1, 2059, 838),Info(2, 3529, 9595),Info(1, 1923, 8647),Info(2, 6866, 9823),Info(1, 5748, 3047),Info(1, 8767, 1458),Info(1, 780, 1571),Info(1, 2426, 6364),Info(2, 8702, 6115),Info(1, 6108, 2341),Info(1, 1211, 9394),Info(2, 5210, 3568),Info(2, 3915, 8892),Info(1, 3943, 6841),Info(2, 9829, 1502),Info(1, 1120, 2275),Info(2, 3305, 1148),Info(1, 4872, 6126),Info(2, 2714, 8017),Info(2, 1670, 6168),Info(1, 9971, 3503),Info(2, 6308, 1901),Info(2, 3736, 9050),Info(1, 7772, 4136),Info(2, 410, 8978),Info(2, 6234, 552),Info(1, 9963, 2686),Info(1, 9141, 9198),Info(1, 2013, 6936),Info(1, 8698, 4581),Info(1, 3162, 2572),Info(2, 9208, 7581),Info(1, 3900, 6694),Info(1, 1461, 2946),Info(1, 7956, 2404),Info(2, 6585, 6218),Info(1, 3612, 6438),Info(1, 1954, 8483),Info(1, 1471, 8789),Info(1, 1027, 3950),Info(1, 6279, 1579),Info(2, 3588, 9350),Info(2, 612, 4690),Info(2, 7338, 6514),Info(2, 7898, 1998),Info(1, 3620, 5325),Info(1, 5601, 2404),Info(1, 5391, 5898),Info(1, 6323, 5611),Info(2, 4040, 6139),Info(2, 8537, 6273),Info(2, 4972, 7064),Info(1, 6659, 497),Info(1, 4256, 5736),Info(1, 9816, 2017),Info(1, 8472, 8115),Info(2, 659, 2390),Info(2, 1703, 3798),Info(1, 6447, 8399),Info(1, 9323, 7322),Info(1, 1276, 4146),Info(2, 2920, 7119),Info(2, 1366, 1623),Info(1, 5263, 4439),Info(2, 809, 8003),Info(2, 7712, 3019),Info(1, 3660, 1866),Info(2, 661, 6),Info(2, 4541, 1218),Info(1, 1311, 5328),Info(1, 6556, 6463),Info(2, 6556, 2905),Info(1, 9606, 8687),Info(2, 9635, 8),Info(2, 6568, 7633),Info(1, 819, 9418),Info(1, 7726, 6457),Info(1, 3331, 8701),Info(1, 5310, 1997),Info(2, 287, 6185),Info(1, 6452, 6221),Info(1, 8790, 7603),Info(1, 6008, 8281),Info(1, 1053, 1222),Info(1, 8019, 50),Info(2, 4029, 380),Info(1, 424, 1287),Info(2, 110, 7093),Info(2, 7904, 4150),Info(1, 8978, 8841),Info(2, 1025, 7786),Info(2, 9467, 4496),Info(2, 908, 7300),Info(2, 1031, 2191),Info(2, 1940, 457),Info(1, 7343, 3079),Info(1, 9335, 1631),Info(1, 1793, 8670),Info(1, 2142, 6854),Info(1, 6044, 5684),Info(2, 8372, 1294),Info(2, 1148, 218),Info(1, 1351, 4041),Info(1, 31, 131),Info(2, 9882, 1985),Info(2, 8146, 10076),Info(2, 1595, 3062),Info(2, 5750, 9385),Info(1, 7851, 8438),Info(1, 716, 4242),Info(1, 1950, 8499),Info(1, 609, 9107),Info(2, 6198, 6538),Info(2, 563, 7063),Info(1, 8209, 7843),Info(1, 56, 2567),Info(2, 8148, 119),Info(1, 7244, 9300),Info(1, 1629, 4533),Info(1, 8025, 2537),Info(1, 6939, 7698),Info(2, 2691, 9896),Info(2, 3316, 7647),Info(1, 8403, 8438),Info(1, 7986, 7130),Info(2, 5272, 8068),Info(1, 1467, 9207),Info(1, 149, 7641),Info(1, 7974, 2283),Info(2, 8272, 8435),Info(2, 8469, 2249),Info(1, 9968, 8429),Info(1, 6173, 7892),Info(2, 2841, 6597),Info(1, 6591, 2824),Info(2, 8795, 5780),Info(2, 10042, 4781),Info(2, 3556, 5160),Info(1, 6781, 3034),Info(1, 5909, 3528),Info(2, 9708, 3897),Info(1, 350, 3918),Info(1, 3264, 9039),Info(2, 8494, 870),Info(1, 2775, 6030),Info(1, 125, 9333),Info(2, 7648, 2850),Info(2, 3681, 9031),Info(1, 1819, 4156),Info(2, 8496, 3189),Info(1, 6744, 6116),Info(2, 6031, 3645),Info(1, 6643, 4681),Info(2, 3310, 175),Info(1, 2, 3338),Info(2, 6685, 6626),Info(1, 5396, 144),Info(2, 5115, 8143),Info(1, 8149, 5342),Info(1, 9483, 6241),Info(2, 664, 2733),Info(2, 1849, 2737),Info(2, 7767, 7311),Info(1, 8394, 5792),Info(2, 2590, 722),Info(1, 2894, 5850),Info(1, 3791, 2277),Info(1, 429, 7392),Info(1, 3319, 4068),Info(2, 2275, 2219),Info(2, 4514, 6646),Info(1, 8525, 8565),Info(2, 7896, 3168),Info(2, 8730, 8111),Info(2, 3852, 3396),Info(1, 4890, 960),Info(2, 5381, 1993),Info(2, 2478, 5676),Info(2, 5172, 6224),Info(2, 6961, 2931),Info(1, 9443, 4731),Info(1, 3377, 5611),Info(2, 4066, 4018),Info(1, 7935, 8269),Info(1, 7901, 7178),Info(2, 3072, 1690),Info(2, 3332, 6260),Info(2, 5968, 6320),Info(2, 7025, 7083),Info(1, 3986, 8656),Info(2, 6608, 4230),Info(2, 4246, 5204),Info(1, 5466, 7772),Info(1, 5794, 2857),Info(2, 36, 2337),Info(1, 2279, 3256),Info(1, 9916, 438),Info(2, 2350, 5249),Info(2, 8216, 6034),Info(2, 1012, 8266),Info(2, 6059, 9952),Info(1, 4274, 5658),Info(2, 7258, 9225),Info(1, 80, 5509),Info(1, 6539, 640),Info(1, 1873, 5476),Info(2, 7887, 7978),Info(1, 8229, 1487),Info(1, 3709, 8690),Info(2, 3723, 7794),Info(1, 5888, 8694),Info(1, 7991, 1328),Info(2, 3, 3874),Info(2, 1079, 9500),Info(2, 8555, 9595),Info(2, 9132, 1386),Info(2, 5274, 8838),Info(2, 6674, 5692),Info(1, 6152, 8602),Info(1, 4080, 8139),Info(1, 3702, 542),Info(2, 2804, 984),Info(1, 1000, 5973),Info(2, 2395, 3902),Info(1, 9178, 265),Info(1, 357, 8240),Info(1, 172, 3245),Info(1, 3963, 7790),Info(1, 6722, 505),Info(1, 4998, 7480),Info(1, 4697, 984),Info(1, 5555, 173),Info(1, 3630, 4691),Info(2, 8312, 3462),Info(2, 6680, 1679),Info(1, 4545, 7657),Info(1, 2928, 9178),Info(2, 1134, 1446),Info(2, 4776, 5874),Info(1, 2551, 8190),Info(2, 10093, 3195),Info(2, 5966, 1623),Info(1, 8220, 1730),Info(1, 4557, 127),Info(2, 9689, 5004),Info(2, 5955, 609),Info(1, 9675, 439),Info(2, 9675, 5533),Info(1, 4797, 4291),Info(2, 3608, 9539),Info(1, 382, 5264),Info(2, 8603, 719),Info(2, 5865, 3571),Info(1, 85, 6417),Info(2, 1804, 9762),Info(1, 948, 6152),Info(2, 9591, 7333),Info(2, 4885, 5276),Info(1, 1248, 5753),Info(2, 4305, 6891),Info(1, 1771, 6616),Info(1, 4767, 9945),Info(2, 5552, 429),Info(1, 4430, 2088),Info(1, 7711, 9976),Info(1, 1604, 978),Info(1, 1412, 4302),Info(1, 7086, 5027),Info(2, 5269, 1),Info(2, 4223, 9643),Info(2, 5515, 1755),Info(2, 9663, 7078),Info(1, 9786, 3062),Info(1, 551, 1176),Info(2, 2412, 7424),Info(2, 9935, 8132),Info(1, 9805, 6724),Info(2, 332, 6239),Info(1, 6164, 5238),Info(1, 3178, 4906),Info(1, 778, 8494),Info(1, 2923, 4894),Info(2, 2790, 1147),Info(1, 2915, 249),Info(2, 6088, 2618),Info(1, 4081, 9601),Info(2, 6282, 8920),Info(1, 6318, 2022),Info(1, 234, 4571),Info(2, 1340, 4922),Info(2, 4330, 1561),Info(1, 4997, 8616),Info(2, 5703, 1673),Info(2, 6227, 5830),Info(1, 9863, 1029),Info(1, 3251, 4501),Info(2, 549, 5777),Info(1, 2194, 878),Info(2, 1500, 40),Info(1, 1801, 3349),Info(1, 7219, 1346),Info(1, 48, 490),Info(2, 9580, 665),Info(1, 4806, 7985),Info(1, 5538, 1878),Info(2, 6429, 4395),Info(1, 4392, 3857),Info(1, 7712, 4166),Info(2, 3496, 219),Info(1, 2083, 3421),Info(2, 131, 9848),Info(1, 6156, 10047),Info(2, 9656, 3810),Info(2, 7584, 7542),Info(2, 6689, 68),Info(2, 196, 277),Info(1, 3089, 5143),Info(1, 9838, 1582),Info(2, 8454, 9612),Info(1, 9331, 9717),Info(2, 1870, 5695),Info(1, 1860, 4702),Info(1, 6238, 2344),Info(2, 5957, 1692),Info(1, 8994, 8432),Info(2, 5561, 2252),Info(1, 5446, 6116),Info(1, 10091, 7447),Info(1, 9847, 5945),Info(2, 4487, 7949),Info(1, 6150, 10034),Info(1, 8672, 6733),Info(1, 5283, 2468),Info(1, 1037, 3274),Info(2, 3517, 768),Info(1, 5853, 7999),Info(2, 2717, 2574),Info(2, 1823, 24),Info(1, 7592, 3702),Info(1, 4982, 9368),Info(1, 6806, 4141),Info(2, 6928, 1358),Info(1, 9367, 2286),Info(1, 9868, 4995),Info(2, 6651, 8459),Info(2, 4745, 9861),Info(1, 2944, 458),Info(2, 8297, 852),Info(1, 758, 1426),Info(1, 10036, 4929),Info(2, 2229, 8336),Info(2, 5648, 2023),Info(1, 7882, 5612),Info(1, 7911, 7813),Info(2, 4106, 8952),Info(2, 5318, 9792),Info(1, 715, 6197),Info(1, 5068, 3991),Info(2, 3464, 9520),Info(2, 6557, 3173),Info(1, 3617, 7706),Info(2, 7671, 807),Info(2, 2862, 9254),Info(1, 2477, 9046),Info(2, 9244, 8065),Info(1, 7679, 3424),Info(2, 6900, 340),Info(1, 6737, 1865),Info(1, 144, 8535),Info(1, 9654, 1721),Info(2, 481, 10068),Info(2, 4043, 2409),Info(2, 9726, 513),Info(2, 1006, 5607),Info(1, 7454, 4188),Info(1, 4103, 627),Info(2, 9692, 6710),Info(1, 1357, 3840),Info(2, 2751, 9122),Info(2, 2137, 2930),Info(2, 5981, 1422),Info(1, 2510, 7906),Info(2, 432, 4020),Info(2, 1787, 2477),Info(2, 5632, 6673),Info(2, 5126, 5845),Info(2, 5494, 8267),Info(1, 5289, 7417),Info(1, 9412, 8150),Info(1, 5301, 8478),Info(1, 5349, 9010),Info(1, 7610, 9858),Info(2, 4984, 5548),Info(1, 3639, 7080),Info(1, 7828, 6223),Info(2, 3335, 9190),Info(2, 87, 1757),Info(1, 5658, 4852),Info(2, 1127, 4311),Info(1, 6087, 4056),Info(2, 2433, 1132),Info(1, 8477, 1835),Info(1, 2129, 3241),Info(2, 2257, 4312),Info(2, 5647, 880),Info(1, 420, 1311),Info(1, 7973, 1628),Info(2, 4184, 4309),Info(1, 9973, 2179),Info(2, 865, 5649),Info(1, 8898, 5773),Info(2, 6980, 4816),Info(1, 2818, 6793),Info(1, 4475, 8190),Info(2, 1903, 5239),Info(1, 3683, 8146),Info(2, 9061, 40),Info(1, 7182, 2278),Info(2, 5539, 8293),Info(2, 9196, 8971),Info(2, 7204, 2626),Info(2, 6879, 5613),Info(2, 5680, 1044),Info(2, 6660, 5473),Info(2, 4217, 6947),Info(1, 4486, 5576),Info(1, 9924, 1594),Info(1, 4571, 2058),Info(2, 3015, 2583),Info(1, 5980, 4498),Info(2, 4579, 3583),Info(2, 2107, 1938),Info(1, 2769, 2825),Info(2, 9764, 8600),Info(2, 5126, 5912),Info(1, 1010, 8712),Info(2, 6410, 9773),Info(2, 5458, 8950),Info(1, 10078, 8107),Info(1, 7674, 8221),Info(1, 2956, 8310),Info(2, 2005, 9413),Info(1, 4634, 9404),Info(2, 478, 7870),Info(1, 429, 5857),Info(2, 3142, 2820),Info(2, 9114, 4819),Info(1, 5830, 8708),Info(1, 9485, 1460),Info(1, 7664, 2992),Info(2, 909, 3399),Info(2, 2689, 7341),Info(2, 2463, 7795),Info(2, 661, 7304),Info(1, 9420, 2884),Info(2, 1077, 10093),Info(2, 3541, 5224),Info(1, 3839, 2724),Info(2, 9567, 6109),Info(1, 7152, 4799),Info(1, 7947, 2350),Info(2, 7503, 5278),Info(1, 747, 1396),Info(1, 930, 6054),Info(2, 399, 7548),Info(1, 2101, 9249),Info(2, 388, 557),Info(1, 8984, 2627),Info(2, 6626, 298),Info(2, 534, 1227),Info(1, 4637, 7807),Info(1, 5543, 8341),Info(1, 4529, 6456),Info(2, 1357, 6187),Info(1, 5930, 9799),Info(2, 7826, 1252),Info(1, 1230, 2631),Info(2, 2976, 6189),Info(1, 6116, 3933),Info(1, 9149, 2632),Info(2, 7821, 1460),Info(2, 8820, 3032),Info(2, 2867, 3696),Info(1, 1990, 8947),Info(1, 3469, 6693),Info(1, 3850, 6499),Info(1, 6136, 9985),Info(1, 7961, 2997),Info(1, 933, 6805),Info(2, 3947, 10020),Info(1, 4834, 3938),Info(1, 9299, 529),Info(1, 4628, 461),Info(2, 1176, 517),Info(1, 8879, 4216),Info(1, 8655, 6797),Info(1, 8186, 8999),Info(2, 242, 2836),Info(1, 5358, 7215),Info(2, 377, 9938),Info(1, 8672, 9745),Info(2, 8533, 6298),Info(1, 4984, 128),Info(1, 7110, 6796),Info(2, 928, 8304),Info(2, 2051, 5999),Info(2, 8515, 6382),Info(1, 8388, 5754),Info(1, 9083, 9095),Info(1, 232, 9864),Info(1, 4240, 6698),Info(1, 6380, 4023),Info(2, 4378, 9454),Info(2, 6620, 1146),Info(2, 9424, 3852),Info(1, 302, 3512),Info(1, 1719, 8218),Info(2, 7884, 8462),Info(1, 2437, 2773),Info(2, 3347, 4096),Info(1, 9283, 4252),Info(2, 2875, 919),Info(1, 987, 2877),Info(2, 5423, 6295),Info(1, 5617, 6001),Info(1, 6142, 1723),Info(1, 9117, 7233),Info(1, 10067, 3202),Info(1, 6707, 6250),Info(2, 3382, 6735),Info(1, 885, 9336),Info(2, 8052, 5235),Info(2, 2612, 1573),Info(1, 3220, 6692),Info(2, 3880, 10068),Info(1, 9069, 4661),Info(1, 491, 1465),Info(2, 6854, 2863),Info(1, 6423, 1107),Info(2, 388, 3767),Info(1, 6424, 4190),Info(2, 2908, 4000),Info(2, 1080, 2414),Info(1, 1272, 9987),Info(2, 2894, 8821),Info(1, 3992, 6720),Info(1, 7888, 2432),Info(2, 3947, 797),Info(1, 5748, 2252),Info(2, 3911, 7326),Info(2, 6139, 8929),Info(1, 9198, 2269),Info(1, 1678, 5110),Info(2, 7847, 2511),Info(1, 2667, 8325),Info(2, 3668, 9433),Info(1, 251, 3397),Info(1, 2398, 1671),Info(2, 2107, 6125),Info(1, 8583, 8334),Info(2, 1685, 2065),Info(1, 50, 3902),Info(1, 7174, 7894),Info(2, 7161, 1816),Info(1, 2337, 9391),Info(2, 3641, 1320),Info(2, 7266, 7471),Info(2, 2019, 9157),Info(1, 2223, 9564),Info(1, 1226, 5076),Info(2, 7804, 9997),Info(1, 5912, 4490),Info(2, 5602, 7662),Info(1, 6301, 9147),Info(1, 1228, 1360),Info(1, 6555, 1846),Info(1, 7694, 4704),Info(2, 7681, 8003),Info(2, 6271, 2717),Info(2, 1937, 4783),Info(1, 4841, 4184),Info(2, 9203, 1956),Info(2, 10089, 5973),Info(1, 5436, 6151),Info(2, 5269, 4091),Info(2, 9466, 7709),Info(2, 1674, 7679),Info(1, 3510, 9977),Info(2, 3567, 8559),Info(1, 7924, 3103),Info(2, 8701, 5169),Info(1, 2728, 3250),Info(2, 7924, 2003),Info(1, 7424, 1657),Info(1, 9315, 1078),Info(2, 6173, 5846),Info(2, 4080, 7202),Info(1, 1619, 91),Info(2, 8036, 5595),Info(1, 4286, 5252),Info(2, 4677, 3163),Info(2, 3885, 1918),Info(2, 4351, 9784),Info(1, 9508, 369),Info(2, 7982, 9230),Info(1, 5186, 5278),Info(1, 5263, 1470),Info(1, 547, 2703),Info(1, 174, 1232),Info(1, 4201, 1217),Info(1, 6452, 6500),Info(1, 5334, 7801),Info(1, 4157, 3178),Info(1, 426, 9872),Info(1, 515, 6920),Info(2, 7340, 3161),Info(1, 8646, 6806),Info(1, 7006, 9199),Info(2, 1080, 4533),Info(1, 8097, 4922),Info(1, 9913, 7806),Info(2, 5579, 9873),Info(2, 6511, 4763),Info(2, 5270, 5333),Info(1, 8890, 3449),Info(2, 4027, 8663),Info(2, 5183, 5548),Info(1, 9713, 8600),Info(2, 2406, 5638),Info(2, 8539, 2444),Info(2, 3709, 6848),Info(1, 7031, 2812),Info(2, 1192, 1698),Info(2, 9333, 6976),Info(1, 9235, 10084),Info(2, 4234, 9299),Info(1, 9851, 993),Info(2, 7497, 2917),Info(1, 6664, 3341),Info(2, 8735, 8596),Info(2, 3737, 3367),Info(2, 6311, 2669),Info(2, 8208, 4854),Info(1, 1643, 4540),Info(1, 3442, 8526),Info(2, 4744, 3298),Info(1, 6, 3080),Info(2, 586, 7829),Info(2, 3464, 7896),Info(2, 981, 3849),Info(1, 3846, 1141),Info(2, 2944, 2503),Info(1, 1871, 7689),Info(2, 4103, 7172),Info(1, 1762, 477),Info(1, 3398, 999),Info(2, 6468, 1280),Info(2, 2407, 5340),Info(2, 6396, 4620),Info(2, 840, 1433),Info(1, 9477, 4836),Info(1, 2255, 8116),Info(2, 5240, 1583),Info(2, 2256, 3532),Info(2, 4797, 4166),Info(1, 2588, 764),Info(2, 9144, 2223),Info(1, 883, 1065),Info(1, 4305, 7604),Info(2, 4719, 10027),Info(1, 415, 289),Info(1, 9225, 78),Info(2, 6138, 5535),Info(2, 3118, 5648),Info(1, 4217, 1379),Info(1, 7558, 7223),Info(1, 3768, 5766),Info(1, 2711, 6324),Info(1, 3258, 1366),Info(2, 7265, 10062),Info(1, 787, 4294),Info(2, 8633, 114),Info(2, 5549, 6462),Info(2, 973, 4372),Info(2, 4916, 9148),Info(2, 7364, 2687),Info(1, 278, 6573),Info(2, 6237, 729),Info(1, 7052, 6973),Info(2, 7284, 8943),Info(1, 2212, 7898),Info(2, 8105, 4230),Info(2, 1245, 8402),Info(1, 6092, 5565),Info(2, 6926, 2213),Info(2, 6390, 4895),Info(1, 198, 9799),Info(1, 8448, 4124),Info(2, 8861, 3538),Info(1, 23, 6665),Info(2, 5401, 7211),Info(1, 2602, 8017),Info(1, 3896, 800),Info(1, 10029, 7706),Info(1, 356, 5176),Info(1, 9493, 7855),Info(2, 5898, 7129),Info(2, 1477, 8537),Info(1, 6982, 4389),Info(1, 1347, 4191),Info(2, 5873, 815),Info(2, 6577, 7281),Info(2, 6959, 1292),Info(1, 8512, 6450),Info(1, 6543, 1499),Info(1, 7952, 5464),Info(1, 8907, 1802),Info(1, 9179, 1838),Info(2, 3515, 4941),Info(1, 8674, 664),Info(2, 8629, 3942),Info(2, 3539, 6383),Info(1, 4973, 9311),Info(1, 4263, 3274),Info(2, 6037, 3949),Info(1, 1441, 8807),Info(2, 4272, 8465),Info(1, 1312, 7466),Info(2, 4721, 2378),Info(1, 7058, 7740),Info(2, 9792, 7807),Info(1, 980, 4101),Info(1, 4150, 2784),Info(1, 1187, 796),Info(2, 4603, 8667),Info(2, 2161, 9839),Info(2, 4023, 6765),Info(2, 4454, 3521),Info(1, 8613, 7988),Info(2, 8305, 6554),Info(1, 4252, 6436),Info(2, 8157, 975),Info(2, 4716, 325),Info(1, 2401, 1926),Info(2, 5501, 9415),Info(2, 7704, 863),Info(2, 9368, 6213),Info(1, 129, 8725),Info(1, 7801, 3290),Info(1, 8231, 45),Info(2, 9418, 159),Info(2, 4917, 2637),Info(1, 3836, 6874),Info(2, 4753, 2927),Info(1, 1336, 6861),Info(2, 9506, 9708),Info(2, 2398, 4487),Info(2, 6977, 8939),Info(2, 8490, 6706),Info(2, 951, 801),Info(2, 4446, 96),Info(1, 6547, 4944),Info(2, 6802, 2771),Info(2, 7207, 4437),Info(2, 3969, 7026),Info(1, 2883, 1773),Info(2, 7037, 3954),Info(1, 3071, 3246),Info(2, 2996, 7653),Info(1, 2654, 9097),Info(1, 8385, 5697),Info(1, 4520, 9465),Info(2, 2979, 7365),Info(1, 1480, 743),Info(2, 7741, 3679),Info(2, 3213, 3874),Info(2, 5098, 9083),Info(1, 205, 5164),Info(2, 3401, 4168),Info(2, 911, 567),Info(2, 5626, 7793),Info(2, 2805, 3472),Info(2, 8634, 9249),Info(1, 5489, 9816),Info(1, 2817, 8979),Info(1, 3290, 9918),Info(2, 3902, 8521),Info(2, 5349, 4388),Info(2, 6431, 5777))
    /**
     * 動物の種類をA,B,Cの3種類定義
     * AはBを食べ、BはCを食べ、CはAを食べる
     */
    sealed abstract class AnimalType
    object A extends AnimalType
    object B extends AnimalType
    object C extends AnimalType
    val animalTypes = Seq(A, B, C)

    /**
     * 各順番の動物の種類の候補を定義する値オブジェクト
     * @param number
     * @param animalType
     */
    case class Candidate(number:Int, animalType:AnimalType)

    val doubtSet = mutable.Set[Info]()
    val ufTree = new UnionFindSet[Candidate]

    infoList.foreach { i => i match {
      case Info(n, x, y) if x > maxNumber || y > maxNumber || x < 1 || y < 1 => doubtSet += i
      case Info(1, x, y) =>
        // xとyが同じという情報は、xのAとyのBが同じだったり、xのAとyのCが同じだったりしたら嘘
        if(ufTree.same(Candidate(x, A), Candidate(y, B))
          || ufTree.same(Candidate(x, A), Candidate(y, C))) {
          doubtSet += i
        } else {
          // 3種類の候補を同じグループとして統合
          animalTypes.foreach(t => ufTree.union(Candidate(x, t), Candidate(y, t)))
        }
      case Info(2, x, y) =>
        // xはyを食べるという情報は、 xのAとyのAが同じだったり、xのAとyのCが同じだったりしたら嘘
        if(ufTree.same(Candidate(x, A), Candidate(y, A))
          || ufTree.same(Candidate(x, A), Candidate(y, C))) {
          doubtSet += i
        } else {
          // 食べる情報が正しいするならば以下の3つ関係性候補を統合
          ufTree.union(Candidate(x, A), Candidate(y, B))
          ufTree.union(Candidate(x, B), Candidate(y, C))
          ufTree.union(Candidate(x, C), Candidate(y, A))
        }
    }}

    println(" failSet.size = " + doubtSet.size)
    println(" failSet = " + doubtSet)

  }
}

// https://github.com/xerial/silk/blob/4f06b307c0a873b529446cc3ca6b1fa261f985d0/src/main/scala/xerial/silk/util/UnionFindSet.scala
// add same() method

/*
 * Copyright 2012 Taro L. Saito
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

//--------------------------------------
//
// UnionFind.scala
// Since: 2012/04/05 15:03
//
//--------------------------------------

/**
 * Union-find based disjoint set implementation.
 *
 * Reference: http://enwikipeida.org/wiki/Disjoint-set_data_structure
 *
 * @author leo
 *
 */
class UnionFindSet[E] extends collection.mutable.Set[E] {

  /**
   * Holder of the element with its rank and the parent node
   * @param elem
   * @param parent
   * @param rank
   */
  private class Container(val elem: E, var parent: E, var rank: Int) {
    def isRoot : Boolean = elem == parent
  }

  /**
   * Hold a map from elements to their containers
   */
  private val elemToContainerIndex = collection.mutable.Map[E, Container]()


  /**
   * Retrieve the container of the element e
   * @param e
   * @return container of e
   */
  private def containerOf(e: E): Container = {
    def newContainer = new Container(e, e, 0) // Set the parent to this element

    // If no container for e is found, create a new one
    elemToContainerIndex.getOrElseUpdate(e, newContainer)
  }

  /**
   * Add a new element
   * @param e
   */
  def +=(e: E): this.type = {
    containerOf(e) // create a new containerOf for e if it does not exist
    this
  }

  def -=(e: E): this.type = {
    throw new UnsupportedOperationException("removal")
  }

  /**
   * Find the representative (root) element of the class to which e belongs
   * @param e
   * @return
   */
  def find(e: E) : E = {
    val c = containerOf(e)
    if(c.isRoot)
      e
    else {
      // path compression: the parent of e
      c.parent = find(c.parent)
      c.parent
    }
  }

  /**
   * if both have the same root, return true
   * @param x
   * @param y
   * @return
   */
  def same(x: E, y: E) : Boolean = {
    find(x) == find(y)
  }

  /**
   * Union the two sets containing x and y
   * @param x
   * @param y
   */
  def union(x: E, y: E) {

    val xRoot = containerOf(find(x))
    val yRoot = containerOf(find(y))

    // Compare the rank of two root nodes
    if (xRoot.rank > yRoot.rank) {
      // x has a higher rank
      yRoot.parent = xRoot.elem
    }
    else {
      // y has a higher rank
      xRoot.parent = yRoot.elem
      // If the ranks are the same, increase the rank of the other
      if (xRoot.rank == yRoot.rank)
        yRoot.rank += 1
    }
  }

  private def containerList = elemToContainerIndex.values

  override def size = elemToContainerIndex.size

  def contains(e: E) = elemToContainerIndex.contains(e)

  /**
   * Iterator of the elements contained in this set
   * @return
   */
  def iterator = containerList.map(_.elem).toIterator

  /**
   * Iterator of the root nodes of the groups
   * @return
   */
  def representatives: Iterable[E] =
    for(c <- containerList if c.isRoot) yield c.elem


  /**
   * Return the elements belonging to the same group with e
   * @param e
   * @return
   */
  def elementsInTheSameClass(e: E) : Iterable[E] = {
    val root = containerOf(find(e))
    for(c <- containerList if find(c.elem) == root.elem) yield c.elem
  }

  /**
   * Iterator of each group
   * @return
   */
  def groups: Iterable[Iterable[E]] =
    for (r <- representatives) yield elementsInTheSameClass(r)

}
