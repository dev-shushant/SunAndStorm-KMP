import SwiftUI
import shared

@UIApplicationMain
class AppDelegate: NSObject, UIApplicationDelegate {
    var window: UIWindow?
    
    func application(_ application: UIApplication, didFinishLaunchingWithOptions launchOptions: [UIApplication.LaunchOptionsKey: Any]?) -> Bool {
            let controller = AvoidDispose(Main_iosKt.MainViewController())
            controller.view.backgroundColor = .white
            let window = UIWindow(frame: UIScreen.main.bounds)
            window.backgroundColor = .white
            window.rootViewController = controller
            window.makeKeyAndVisible()
            self.window = window
            return true
    }
}
