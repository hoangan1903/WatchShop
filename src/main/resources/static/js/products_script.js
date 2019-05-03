/* Products page functionality implementation */

$(document).ready(function () {

    // Global variables

    var logos = {
        bulova: 'images/bulova-logo.png',
        citizen: 'images/citizen-logo.png',
        ogival: 'images/ogival-logo.jpg',
        orient: 'images/orient-logo.jpg',
        seiko: 'images/seiko-logo.png',
    };

    function Product(id, name, image, price) {
        this.id = id;
        this.name = name;
        this.image = image;
        this.price = price;
    }

    var obj = [
        [
            new Product(1, 'Citizen NP1010-01A', logos.citizen, '12,200,000đ'),
            new Product(2, 'Ogival OG1929-24AGSR-T', logos.ogival, '16,970,000đ'),
            new Product(3, 'Orient SET0T002S0', logos.orient, '9,530,000đ'),
            new Product(4, 'Citizen NH8353-00H', logos.citizen, '5,500,000đ')
        ],
        [
            new Product(5, 'Ogival OG385-021GK-T', logos.ogival, '7,880,000đ'),
            new Product(6, 'Orient FAA02009D9', logos.orient, '6,170,000đ'),
            new Product(7, 'Citizen AR1113-12B', logos.citizen, '8,120,000đ'),
            new Product(8, 'Orient FAC00009W0', logos.orient, '5,360,000đ')
        ],
        [
            new Product(9, 'Citizen BL8144-89H', logos.citizen, '16,500,000đ'),
            new Product(10, 'Ogival OG358.33AGR-GL', logos.ogival, '35,620,000đ'),
            new Product(10, 'Bulova 98B301', logos.bulova, '13,900,000đ'),
            new Product(12, 'Citizen NP1010-01L', logos.citizen, '12,190,000đ')
        ],
        [
            new Product(9, 'Citizen BL8144-89H', logos.citizen, '16,500,000đ'),
            new Product(10, 'Ogival OG358.33AGR-GL', logos.ogival, '35,620,000đ'),
            new Product(10, 'Bulova 98B301', logos.bulova, '13,900,000đ'),
            new Product(12, 'Citizen NP1010-01L', logos.citizen, '12,190,000đ')
        ],
        [
            new Product(9, 'Citizen BL8144-89H', logos.citizen, '16,500,000đ'),
            new Product(10, 'Ogival OG358.33AGR-GL', logos.ogival, '35,620,000đ'),
            new Product(10, 'Bulova 98B301', logos.bulova, '13,900,000đ'),
            new Product(12, 'Citizen NP1010-01L', logos.citizen, '12,190,000đ')
        ],
        [
            new Product(9, 'Citizen BL8144-89H', logos.citizen, '16,500,000đ'),
            new Product(10, 'Ogival OG358.33AGR-GL', logos.ogival, '35,620,000đ'),
            new Product(10, 'Bulova 98B301', logos.bulova, '13,900,000đ'),
            new Product(12, 'Citizen NP1010-01L', logos.citizen, '12,190,000đ')
        ],
        [
            new Product(9, 'Citizen BL8144-89H', logos.citizen, '16,500,000đ'),
            new Product(10, 'Ogival OG358.33AGR-GL', logos.ogival, '35,620,000đ'),
            new Product(10, 'Bulova 98B301', logos.bulova, '13,900,000đ'),
            new Product(12, 'Citizen NP1010-01L', logos.citizen, '12,190,000đ')
        ],
        [
            new Product(13, 'Seiko SSB177P1', logos.seiko, '6,230,000đ'),
            new Product(14, 'Citizen FE1081-59E', logos.citizen, '5,880,000đ'),
            new Product(15, 'Seiko SPB039J1', logos.seiko, '18,010,000đ')
        ]
    ];

    var paginator = new Paginator({
        obj: obj,
        selectors: {
            container: '.section-product-list .row',
            pagination: '.section-pagination-links .pagination'
        },
        paginationStyle: 'advanced'
    });

    paginator.init();
});
