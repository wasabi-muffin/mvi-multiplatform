// swift-tools-version:5.3
import PackageDescription

let package = Package(
    name: "MVIMultiplatform",
    platforms: [
        .iOS(.v14)
    ],
    products: [
        .library(
            name: "MVIMultiplatform",
            targets: ["MVIMultiplatform"]
        ),
    ],
    targets: [
        .binaryTarget(
            name: "MVIMultiplatform",
            path: "./MVIMultiplatform.xcframework"
        ),
    ]
)
