//
//  AvoidDispose.swift
//  iosApp
//
//  Created by Shushant Tiwari on 03/03/23.
//  Copyright © 2023 org.jetbrains. All rights reserved.
//

import UIKit
import shared
import CoreLocation

class AvoidDispose: UIViewController {
    
    @IBOutlet weak var locationLabel: UILabel!
    let controller: UIViewController
    
    deinit {
        SunAndStormLocation.Companion().stopLocationUpdating()
    }

    init(_ controller: UIViewController) {
        self.controller = controller
        KoinKt.doInitKoin()
        super.init(nibName: nil, bundle: nil)
    }

    required init?(coder: NSCoder) {
        fatalError("init(coder:) has not been implemented")
    }

    override func viewWillAppear(_ animated: Bool) {
        super.viewWillAppear(animated)
        addChild(controller)
        view.addSubview(controller.view)
    }
    
//    override func viewDidLoad() {
//        super.viewDidLoad()
//
//    }

    override func viewDidAppear(_ animated: Bool) {
        super.viewDidAppear(animated)
    }

    override func viewDidDisappear(_ animated: Bool) {
        super.viewDidDisappear(animated)
        controller.removeFromParent()
        controller.view.removeFromSuperview()
    }

    override func viewWillTransition(to size: CGSize, with coordinator: UIViewControllerTransitionCoordinator) {
        super.viewWillTransition(to: size, with: coordinator)
        skiaRefresh()
    }

    override func traitCollectionDidChange(_ previousTraitCollection: UITraitCollection?) {
        super.traitCollectionDidChange(previousTraitCollection)
        skiaRefresh()
    }

    override func viewSafeAreaInsetsDidChange() {
        super.viewSafeAreaInsetsDidChange()
        skiaRefresh()
    }

    override var preferredStatusBarStyle: UIStatusBarStyle {
        return .default
    }

    private func skiaRefresh() {
        controller.view.frame = view.bounds
        controller.viewWillAppear(false)
        SunAndStormLocation.Companion().requiredPermission = .authorizedalways
        SunAndStormLocation.Companion().onAlwaysAllowsPermissionRequired(target: self) {
            print("onAlwaysAllowsPermissionRequired")
        }
        Main_iosKt.setDarkMode()
        Main_iosKt.setSafeArea(start: view.safeAreaInsets.left, top: view.safeAreaInsets.top, end: view.safeAreaInsets.right, bottom: view.safeAreaInsets.bottom, width: view.bounds.width, height: view.bounds.height)
        //kotlin compose refresh
        controller.view.touchesCancelled([UITouch()], with: UIEvent())
    }
    
    @IBAction func tappedCurrent(_ sender: UIButton) {
        SunAndStormLocation.Companion()
            .onLocationUnavailable(target: "SingleRequest") { [unowned self] in
                print("onLocationUnavailable")
                showPermissionDeniedAlert()
            }
            .onPermissionUpdated(target: self, block: {
                print("onPermissionUpdated. Granted:", $0)
            })
            .currentLocation { [unowned self] data in
                print("location coordinates", Date(), data.coordinates)
                locationLabel.text = "Single \(data.coordinates.latitude)\n\(locationLabel.text!)"
            }
    }

    @IBAction func tappedStartForegroundLocation(_ sender: UIButton) {
        Task { @MainActor in
            do{
                try await SunAndStormLocation.Companion()
                    .onLocationUnavailable(target: self) { [unowned self] in
                        print("onLocationUnavailable")
                        showPermissionDeniedAlert()
                    }
                    .onLocationUpdated(target: self) { [unowned self] data in
                        print("location coordinates", Date(), data.coordinates)
                        locationLabel.text = "Continuous \(data.coordinates.latitude)\n\(locationLabel.text!)"
                    }
                    .startLocationUpdating()
            }
            catch{
                
            }
        }
    }

    @IBAction func tappedStopForegroundLocation(_ sender: UIButton) {
        SunAndStormLocation.Companion().removeListeners(target: self)
        locationLabel.text = "Loation"
    }

    private func showPermissionDeniedAlert() {
        let defaultMessage = "Location services are not available.\nTurn on location services in the device's \"Setting > Privacy\"."
        let alert = UIAlertController(title: "", message: defaultMessage, preferredStyle: .alert)
        let cancel = UIAlertAction(title: "Cancel", style: .cancel)
        let landing = UIAlertAction(title: "Go to Setting", style: .default) { _ in
            UIApplication.shared.open(URL(string: UIApplication.openSettingsURLString)!)
        }
        alert.addAction(cancel)
        alert.addAction(landing)
        present(alert, animated: true)
    }
}

